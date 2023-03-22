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
}
