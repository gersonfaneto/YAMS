package com.gersonfaneto.techinfo.models.stock;

import com.gersonfaneto.techinfo.models.stock.component.ComponentType;

public class PurchaseOrder {
    private final String technicianID;
    private final ComponentType componentType;
    private final String componentDescription;
    private final int purchasedAmount;
    private final double unitaryPrice;
    private final double unitaryCost;
    private String purchaseOrderID;

    public PurchaseOrder(String technicianID, ComponentType componentType, String componentDescription, int purchasedAmount, double unitaryPrice, double unitaryCost) {
        this.purchaseOrderID = "Undefined!";
        this.technicianID = technicianID;
        this.componentType = componentType;
        this.componentDescription = componentDescription;
        this.purchasedAmount = purchasedAmount;
        this.unitaryPrice = unitaryPrice;
        this.unitaryCost = unitaryCost;
    }

    public PurchaseOrder(String technicianID, ComponentType componentType, String componentDescription, int purchasedAmount, double unitaryCost) {
        this.purchaseOrderID = "Undefined!";
        this.technicianID = technicianID;
        this.componentType = componentType;
        this.componentDescription = componentDescription;
        this.purchasedAmount = purchasedAmount;
        this.unitaryPrice = componentType.getTypeValue();
        this.unitaryCost = unitaryCost;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Technician: %s
                Component: %s
                Description: %s
                Price: R$ %.2f
                Cost: R$: %.2f
                """, purchaseOrderID, technicianID, componentType.getTypeName(), componentDescription, unitaryPrice, unitaryCost);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof PurchaseOrder otherPurchaseOrder) {
            return otherPurchaseOrder.purchaseOrderID.equals(this.purchaseOrderID);
        }

        return false;
    }

    public String getPurchaseOrderID() {
        return purchaseOrderID;
    }

    public void setPurchaseOrderID(String purchaseOrderID) {
        this.purchaseOrderID = purchaseOrderID;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public int getPurchasedAmount() {
        return purchasedAmount;
    }

    public double getUnitaryPrice() {
        return unitaryPrice;
    }

    public double getUnitaryCost() {
        return unitaryCost;
    }
}
