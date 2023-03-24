package com.gersonfaneto.techinfo.models.stock;

public class PurchaseOrder {
    private static int referenceID = 0;
    private ComponentType purchasedComponentType;
    private int purchaseOrderID;
    private int purchasedAmount;
    private double unitaryValue;

    public PurchaseOrder(ComponentType purchasedComponentType, int purchasedAmount, double unitaryValue) {
        this.purchaseOrderID = ++referenceID;
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

    public int getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(int purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    public double getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(double unitaryValue) {
        this.unitaryValue = unitaryValue;
    }
}
