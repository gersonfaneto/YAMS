package com.gersonfaneto.yams.builders.component;

import static com.gersonfaneto.yams.models.components.ComponentType.Others;

import com.gersonfaneto.yams.builders.Builder;
import com.gersonfaneto.yams.exceptions.ComponentTypeNotFound;
import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.components.ComponentType;
import java.security.InvalidParameterException;

public class ComponentBuilder implements Builder<Component> {

  private final ComponentType componentType;
  private String componentDescription;
  private double componentCost;
  private double componentPrice;
  private int amountInStock;

  public ComponentBuilder(ComponentType componentType) {
    this.componentType = componentType;
  }

  public ComponentBuilder defineDescription(String componentDescription) {
    this.componentDescription = componentDescription;
    return this;
  }

  public ComponentBuilder defineCost(double componentCost) {
    this.componentCost = componentCost;
    return this;
  }

  public ComponentBuilder definePrice(double componentPrice) {
    this.componentPrice = (componentType == Others) ? componentPrice : componentType.getTypeValue();
    return this;
  }

  public ComponentBuilder amountInStock(int amountInStock) {
    this.amountInStock = amountInStock;
    return this;
  }

  public Component Build() {
    return new Component(this);
  }

  public ComponentType getComponentType() {
    return componentType;
  }

  public String getComponentDescription() {
    return componentDescription;
  }

  public double getComponentCost() {
    return componentCost;
  }

  public double getComponentPrice() {
    return componentPrice;
  }

  public int getAmountInStock() {
    return amountInStock;
  }
}
