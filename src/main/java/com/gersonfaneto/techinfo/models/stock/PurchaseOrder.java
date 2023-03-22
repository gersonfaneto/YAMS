package com.gersonfaneto.techinfo.models.stock;

public class PurchaseOrder {
    private Component purchasedComponent;
    private Integer purchasedAmount;
    private Double unitaryValue;

    public PurchaseOrder(Component component, Integer amount, Double unitaryValue) {
        this.purchasedComponent = component;
        this.purchasedAmount = amount;
        this.unitaryValue = unitaryValue;
    }

    public Component getPart() {
        return purchasedComponent;
    }

    public void setPart(Component component) {
        this.purchasedComponent = component;
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
