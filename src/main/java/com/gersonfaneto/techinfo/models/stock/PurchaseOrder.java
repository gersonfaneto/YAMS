package com.gersonfaneto.techinfo.models.stock;

public class PurchaseOrder {
    private ComponentType purchasedComponentType;
    private int purchaseOrderID;
    private int technicianID;
    private int purchasedAmount;
    private double unitaryValue;

    public PurchaseOrder(ComponentType purchasedComponentType, int technicianID, int purchasedAmount, double unitaryValue) {
        this.purchasedComponentType = purchasedComponentType;
        this.technicianID = technicianID;
        this.purchasedAmount = purchasedAmount;
        this.unitaryValue = unitaryValue;
    }

    public int getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(int purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public int getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(int technicianID) {
        this.technicianID = technicianID;
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
