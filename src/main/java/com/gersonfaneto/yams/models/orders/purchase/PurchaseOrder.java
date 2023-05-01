package com.gersonfaneto.yams.models.orders.purchase;

import static com.gersonfaneto.yams.models.stock.ComponentType.Others;

import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.io.Serializable;

/**
 * Represent the Purchase Orders that can be made by the Technicians for restocking the Assistance.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see Component
 */
public class PurchaseOrder implements Serializable {

  private String purchaseOrderID;
  private ComponentType componentType;
  private String componentDescription;
  private int boughtAmount;
  private double componentCost;
  private double componentPrice;

  /**
   * Constructs a new <code>PurchaseOrder</code> for a specific <code>Component</code>.
   *
   * @param componentType        The <code>ComponentType</code> to be bought.
   * @param componentDescription The <code>Component</code> description.
   * @param boughtAmount         The amount bought.
   * @param componentCost        The cost of each <code>Component</code>.
   * @param componentPrice       The price of each <code>Component</code>.
   */
  public PurchaseOrder(
      ComponentType componentType,
      String componentDescription,
      int boughtAmount,
      double componentCost,
      double componentPrice
  ) {
    this.componentType = componentType;
    this.componentDescription = componentDescription;
    this.boughtAmount = boughtAmount;
    this.componentCost = componentCost;
    this.componentPrice = (componentType == Others) ? componentPrice : componentType.getTypeValue();
  }

  /**
   * Compares an <code>Object</code> to the <code>PurchaseOrder</code>.
   *
   * @param otherObject The <code>Object</code> to be compared to.
   * @return <code>true</code> if the objects match, or <code>false</code> if they don't.
   */
  @Override
  public boolean equals(Object otherObject) {
    // Checking if the Object passed isn't the PurchaseOrder itself.
    if (this == otherObject) {
      return true;
    }

    // Checking if the Object passed is null.
    if (otherObject == null) {
      return false;
    }

    // Checking if the Object passed is from the Class PurchaseOrder and casting it.
    if (!(otherObject instanceof PurchaseOrder otherPurchaseOrder)) {
      return false;
    }

    // Comparing by the IDs.
    return purchaseOrderID.equals(otherPurchaseOrder.purchaseOrderID);
  }

  /**
   * Generate a <code>String</code> from the most important information of the <code>PurchaseOrder
   * </code>.
   *
   * @return Relevant information about the <code>PurchaseOrder</code>.
   */
  @Override
  public String toString() {
    return String.format("""
            ID: %s
            Type: %s
            Description: %s
            Amount: %d
            Cost: R$ %.2f
            Price: R$ %.2f
            """,
        purchaseOrderID,
        componentType,
        componentDescription,
        boughtAmount,
        componentCost,
        componentPrice);
  }

  public String getPurchaseOrderID() {
    return purchaseOrderID;
  }

  public void setPurchaseOrderID(String purchaseOrderID) {
    this.purchaseOrderID = purchaseOrderID;
  }

  public ComponentType getComponentType() {
    return componentType;
  }

  public void setComponentType(ComponentType componentType) {
    this.componentType = componentType;
  }

  public String getComponentDescription() {
    return componentDescription;
  }

  public void setComponentDescription(String componentDescription) {
    this.componentDescription = componentDescription;
  }

  public int getBoughtAmount() {
    return boughtAmount;
  }

  public void setBoughtAmount(int boughtAmount) {
    this.boughtAmount = boughtAmount;
  }

  public double getComponentCost() {
    return componentCost;
  }

  public void setComponentCost(double componentCost) {
    this.componentCost = componentCost;
  }

  public double getComponentPrice() {
    return componentPrice;
  }

  public void setComponentPrice(double componentPrice) {
    this.componentPrice = componentPrice;
  }
}
