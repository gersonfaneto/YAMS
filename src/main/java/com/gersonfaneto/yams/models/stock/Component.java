package com.gersonfaneto.yams.models.stock;

import static com.gersonfaneto.yams.models.stock.ComponentType.Others;

public class Component {

  private String componentID;
  private ComponentType componentType;
  private String componentDescription;
  private double componentCost;
  private double componentPrice;
  private int amountInStock;

  public Component(ComponentType componentType,
      String componentDescription,
      int amountInStock,
      double componentCost,
      double componentPrice
  ) {
    this.componentType = componentType;
    this.componentDescription = componentDescription;
    this.componentCost = componentCost;
    this.componentPrice = (componentType == Others) ? componentPrice : componentType.getTypeValue();
    this.amountInStock = amountInStock;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }

    if (!(otherObject instanceof Component otherComponent)) {
      return false;
    }

    return componentType.equals(otherComponent.componentType) &&
        componentDescription.equals(otherComponent.componentDescription) &&
        componentPrice == otherComponent.getComponentPrice() &&
        componentCost == otherComponent.getComponentCost();
  }

  @Override
  public String toString() {
    return String.format("""
            ID: %s
            Type: %s
            Description: %s
            Cost: R$ %.2f
            Price: R$ %.2f
            """,
        componentID, componentType, componentDescription, componentCost, componentPrice
    );
  }

  public String getComponentID() {
    return componentID;
  }

  public void setComponentID(String componentID) {
    this.componentID = componentID;
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

  public int getAmountInStock() {
    return amountInStock;
  }

  public void setAmountInStock(int amountInStock) {
    this.amountInStock = amountInStock;
  }
}
