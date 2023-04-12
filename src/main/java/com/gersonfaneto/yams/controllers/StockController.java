package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.stock.ComponentTypeNotFound;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import java.util.List;

public abstract class StockController {

  public static List<Component> listComponents() {
    return DAO.fromComponents().findMany();
  }

  public static List<Component> listComponents(String componentType) throws ComponentTypeNotFound {
    if (ComponentType.findByType(componentType) == null) {
      throw new ComponentTypeNotFound("Component type not found!");
    }

    return DAO.fromComponents().findByType(ComponentType.findByType(componentType));
  }

  public static Component reserveComponent(String componentID) {
    Component chosenComponent = DAO.fromComponents().findByID(componentID);

    DAO.fromComponents().deleteByID(componentID);

    return chosenComponent;
  }

  public static Component restoreComponent(Component unusedComponent) {
    return DAO.fromComponents().createOne(unusedComponent);
  }

  public static PurchaseOrder buyComponent(
      String componentType,
      String componentDescription,
      double componentPrice,
      double componentCost,
      int boughtAmount
  ) throws ComponentTypeNotFound {
    if (ComponentType.findByType(componentType) == null) {
      throw new ComponentTypeNotFound("Component type not found!");
    }

    PurchaseOrder purchaseOrder = new PurchaseOrder(
        ComponentType.findByType(componentType),
        componentDescription,
        boughtAmount,
        componentPrice,
        componentCost
    );

    return DAO.fromPurchaseOrders().createOne(purchaseOrder);
  }

  public static Component storeBoughtComponents(PurchaseOrder purchaseOrder) {
    Component boughtComponent = new Component(
        purchaseOrder.getComponentType(),
        purchaseOrder.getComponentDescription(),
        purchaseOrder.getBoughtAmount(),
        purchaseOrder.getComponentCost(),
        purchaseOrder.getComponentPrice()
    );

    Component foundComponent = DAO.fromComponents().findEquals(boughtComponent);

    if (foundComponent == null) {
      DAO.fromComponents().createOne(boughtComponent);
    } else {
      foundComponent.setAmountInStock(
          foundComponent.getAmountInStock() + boughtComponent.getAmountInStock()
      );
      DAO.fromComponents().updateInformation(foundComponent);

      return foundComponent;
    }

    return boughtComponent;
  }
}
