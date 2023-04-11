package com.gersonfaneto.yams.models.orders.purchase;

import static com.gersonfaneto.yams.models.components.ComponentType.Others;

import com.gersonfaneto.yams.models.components.ComponentType;

public class PurchaseOrder {

  private String purchaseOrderID;
  private ComponentType componentType;
  private String componentDescription;
  private int boughtAmount;
  private double componentCost;
  private double componentPrice;

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

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }

    if (!(otherObject instanceof PurchaseOrder otherPurchaseOrder)) {
      return false;
    }

    return purchaseOrderID.equals(otherPurchaseOrder.purchaseOrderID);
  }

  @Override
  public String toString() {
    return String.format(
        """
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
