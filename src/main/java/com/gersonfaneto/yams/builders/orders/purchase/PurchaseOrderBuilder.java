package com.gersonfaneto.yams.builders.orders.purchase;

import static com.gersonfaneto.yams.models.components.ComponentType.Others;

import com.gersonfaneto.yams.builders.Builder;
import com.gersonfaneto.yams.models.components.ComponentType;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import java.security.InvalidParameterException;

public class PurchaseOrderBuilder implements Builder<PurchaseOrder> {
  private final ComponentType componentType;
  private String componentDescription;
  private int boughtAmount;
  private double componentCost;
  private double componentPrice;

  public PurchaseOrderBuilder(String componentType) {
    if (ComponentType.findByType(componentType) == null) {
      throw new InvalidParameterException("Component type not known!");
    }
    this.componentType = ComponentType.findByType(componentType);
  }

  public PurchaseOrderBuilder componentDescription(String componentDescription) {
    this.componentDescription = componentDescription;
    return this;
  }

  public PurchaseOrderBuilder boughtAmount(int boughtAmount) {
    this.boughtAmount = boughtAmount;
    return this;
  }

  public PurchaseOrderBuilder costOfEach(double componentCost) {
    this.componentCost = componentCost;
    return this;
  }

  public PurchaseOrderBuilder priceOfEach(double componentPrice) {
    this.componentPrice = (componentType == Others) ? componentPrice : componentType.getTypeValue();
    return this;
  }

  @Override
  public PurchaseOrder Build() {
    return new PurchaseOrder(this);
  }

  public ComponentType getComponentType() {
    return componentType;
  }

  public String getComponentDescription() {
    return componentDescription;
  }

  public int getBoughtAmount() {
    return boughtAmount;
  }

  public double getComponentCost() {
    return componentCost;
  }

  public double getComponentPrice() {
    return componentPrice;
  }
}
