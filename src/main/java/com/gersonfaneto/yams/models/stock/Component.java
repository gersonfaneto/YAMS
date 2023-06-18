package com.gersonfaneto.yams.models.stock;

import com.gersonfaneto.yams.models.services.service.Service;
import java.io.Serializable;

/**
 * Represents each Computer Component that the Assistance might have in Stock, that can be used on
 * the Services.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see Service
 * @see ComponentType
 */
public class Component implements Serializable {

  private String componentID;
  private ComponentType componentType;
  private String componentDescription;
  private double componentCost;
  private double componentPrice;
  private int amountInStock;

  /**
   * @param componentType The type of the <code>Component</code>.
   * @param componentDescription The description of the <code>Component</code>.
   * @param amountInStock The amount currently in stock of the <code>Component</code>.
   * @param componentCost The cost of each <code>Component</code>.
   * @param componentPrice The price of each <code>Component</code>.
   */
  public Component(
      ComponentType componentType,
      String componentDescription,
      int amountInStock,
      double componentCost,
      double componentPrice) {
    this.componentType = componentType;
    this.componentDescription = componentDescription;
    this.componentCost = componentCost;
    this.componentPrice = componentPrice;
    this.amountInStock = amountInStock;
  }

  /**
   * Compares an <code>Object</code> to the <code>Component</code>.
   *
   * @param otherObject The <code>Object</code> to be compared to.
   * @return <code>true</code> if the objects match, or <code>false</code> if they don't.
   */
  @Override
  public boolean equals(Object otherObject) {
    // Checking if the Object passed isn't the Componet itself.
    if (this == otherObject) {
      return true;
    }

    // Checking if the Object passed isn't null.
    if (otherObject == null) {
      return false;
    }

    // Checking if the Object passed is from the Class Component and casting it.
    if (!(otherObject instanceof Component otherComponent)) {
      return false;
    }

    // Comparing each of the Component attributes, besides its "componentID" and "amountInStock".
    return componentType.equals(otherComponent.componentType)
        && componentDescription.equals(otherComponent.componentDescription)
        && componentPrice == otherComponent.getComponentPrice()
        && componentCost == otherComponent.getComponentCost();
  }

  /**
   * Generate a <code>String</code> from the most important information of the <code>Component
   * </code>.
   *
   * @return Relevant information about the <code>Component</code>.
   */
  @Override
  public String toString() {
    return String.format(
        """
        ID: %s
        Type: %s
        Description: %s
        Cost: R$ %.2f
        Price: R$ %.2f
        """,
        componentID, componentType, componentDescription, componentCost, componentPrice);
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
