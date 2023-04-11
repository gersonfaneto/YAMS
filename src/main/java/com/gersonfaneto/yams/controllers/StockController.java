package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.builders.component.ComponentBuilder;
import com.gersonfaneto.yams.builders.orders.purchase.PurchaseOrderBuilder;
import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.ComponentTypeNotFound;
import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.components.ComponentType;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import java.util.List;

public abstract class StockController {

  public static List<Component> listComponents() {
    return DAO.fromComponents().findMany();
  }

  public static List<Component> listComponents(String componentType) throws ComponentTypeNotFound {
    if (ComponentType.findByType(componentType) == null) {
      throw new ComponentTypeNotFound("Component type '" + componentType + " not found!");
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
      throw new ComponentTypeNotFound("Component type '" + componentType + "' not found!");
    }

    PurchaseOrder purchaseOrder = new PurchaseOrderBuilder(ComponentType.findByType(componentType))
        .componentDescription(componentDescription)
        .priceOfEach(componentPrice)
        .costOfEach(componentCost)
        .boughtAmount(boughtAmount)
        .Build();

    return DAO.fromPurchaseOrders().createOne(purchaseOrder);
  }

  public static Component storeBoughtComponents(PurchaseOrder purchaseOrder) {
    ComponentBuilder componentBuilder = new ComponentBuilder(purchaseOrder.getComponentType());

    Component boughtComponent = componentBuilder
        .defineDescription(purchaseOrder.getComponentDescription())
        .defineCost(purchaseOrder.getComponentCost())
        .definePrice(purchaseOrder.getComponentPrice())
        .amountInStock(purchaseOrder.getBoughtAmount())
        .Build();

    if (DAO.fromComponents().findEquals(boughtComponent) == null) {
      DAO.fromComponents().createOne(boughtComponent);
    }
    else {
      Component foundComponent = DAO.fromComponents().findEquals(boughtComponent);
      foundComponent.setAmountInStock(
          foundComponent.getAmountInStock() + boughtComponent.getAmountInStock()
      );
      DAO.fromComponents().updateInformation(foundComponent);

      return foundComponent;
    }

    return boughtComponent;
  }
}
