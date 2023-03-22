package com.gersonfaneto.techinfo.models.stock;

public class PurchaseOrder {
    private ComponentType purchasedComponentType;
    private Integer purchasedAmount;
    private Double unitaryValue;

    public PurchaseOrder(ComponentType purchasedComponentType, Integer purchasedAmount, Double unitaryValue) {
        this.purchasedComponentType = purchasedComponentType;
        this.purchasedAmount = purchasedAmount;
        this.unitaryValue = unitaryValue;
    }

    public ComponentType getPurchasedComponentType() {
        return purchasedComponentType;
    }

    public void setPurchasedComponentType(ComponentType purchasedComponentType) {
        this.purchasedComponentType = purchasedComponentType;
    }

    public Integer getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(Integer purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    public Double getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(Double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }
}
