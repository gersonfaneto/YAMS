package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.orders.purchase.PurchaseOrderCRUD;
import com.gersonfaneto.yams.dao.orders.purchase.PurchaseOrderMemoryDAO;
import com.gersonfaneto.yams.dao.stock.ComponentCRUD;
import com.gersonfaneto.yams.dao.stock.ComponentMemoryDAO;
import com.gersonfaneto.yams.exceptions.stock.ComponentTypeNotFoundException;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;

/**
 * Controller containing all the operations related with the <code>Component</code> and <code>
 * PurchaseOrder</code> models on the System, such as creating, updating and retrieving information
 * about them. Most of the operations relly on the CRUD operations accessed through the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see ComponentMemoryDAO
 * @see ComponentCRUD
 * @see Component
 * @see PurchaseOrderMemoryDAO
 * @see PurchaseOrderCRUD
 * @see PurchaseOrder
 */
public abstract class StockController {

  /**
   * Retrieves all the <code>Component</code>s in Stock.
   *
   * @return A list containing all the <code>Component</code>s in Stock.
   * @see Component
   */
  public static List<Component> listComponents() {
    return DAO.fromComponents().findMany();
  }

  /**
   * Retrieves all the <code>Component</code>s in Stock the match the provided type.
   *
   * @param componentType The targeted <code>Component</code> type.
   * @return The list of all the <code>Component</code>s matching the provided type.
   * @throws ComponentTypeNotFoundException If the <code>componentType</code> provided doesn't match
   *                                        any of the ones declared under the
   *                                        <code>ComponentTypes</code> enumeration.
   * @see Component
   * @see ComponentType
   */
  public static List<Component> listComponents(
      String componentType
  ) throws ComponentTypeNotFoundException {
    if (ComponentType.findByType(componentType) == null) {
      throw new ComponentTypeNotFoundException("Component type not found!");
    }

    return DAO.fromComponents().findByType(ComponentType.findByType(componentType));
  }

  /**
   * Searches for a <code>Component</code> given its ID and subtracts its <code>amountInStock</code>
   * , indicating that its going to be used in a <code>Service</code>.
   *
   * @param componentID The ID of the desired <code>Component</code>.
   * @return The desired <code>Component</code>.
   * @see Component
   */
  public static Component reserveComponent(String componentID) {
    Component chosenComponent = DAO.fromComponents().findByID(componentID);

    chosenComponent.setAmountInStock(chosenComponent.getAmountInStock() - 1);
    DAO.fromComponents().updateInformation(chosenComponent);

    return chosenComponent;
  }

  /**
   * Returns a <code>Component</code> to the Stock and update its <code>amountInStock</code>.
   *
   * @param unusedComponent The unused <code>Component</code>.
   * @return The updated <code>Component</code>.
   * @see Component
   */
  public static Component restoreComponent(Component unusedComponent) {
    unusedComponent.setAmountInStock(unusedComponent.getAmountInStock() + 1);

    return DAO.fromComponents().createOne(unusedComponent);
  }

  /**
   * Generates a new <code>PurchaseOrder</code> for a desired <code>Component</code>.
   *
   * @param componentType        The type of the <code>Component</code> to be bought.
   * @param componentDescription The description of the <code>Component</code>.
   * @param componentPrice       The price of each <code>Component</code>.
   * @param componentCost        The cost of each <code>Component</code>.
   * @param boughtAmount         The amount bought.
   * @return A <code>PurchaseOrder</code> for the desired <code>Component</code>.
   * @throws ComponentTypeNotFoundException If the <code>componentType</code> provided doesn't match
   *                                        any of the ones declared under the
   *                                        <code>ComponentType</code> enumeration.
   * @see PurchaseOrder
   * @see Component
   * @see ComponentType
   */
  public static PurchaseOrder buyComponent(
      String componentType,
      String componentDescription,
      double componentPrice,
      double componentCost,
      int boughtAmount
  ) throws ComponentTypeNotFoundException {
    if (ComponentType.findByType(componentType) == null) {
      throw new ComponentTypeNotFoundException("Component type not found!");
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

  /**
   * Process the information of a <code>PurchaseOrder</code> and creates/stores the <code>Component
   * </code> referred by it.
   *
   * @param purchaseOrder The desired <code>PurchaseOrder</code>.
   * @return The generated <code>Component</code>.
   */
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
