package com.gersonfaneto.techinfo.models.stock;

import java.util.LinkedList;
import java.util.List;

public class Stock {
    private static Stock singleInstance;
    private static List<Component> availableComponents;
    private static List<PurchaseOrder> purchaseOrderHistory;

    private Stock() {
    }

    public static Stock retrieveStock() {
        if (singleInstance == null) {
            singleInstance = new Stock();
            availableComponents = new LinkedList<>();
            purchaseOrderHistory = new LinkedList<>();
        }
        return singleInstance;
    }

    public boolean verifyComponent(ComponentType targetType, int neededAmount) {
        int foundAmount = 0;

        for (Component currentComponent : availableComponents) {
            if (currentComponent.getComponentType() == targetType) {
                foundAmount++;
            }
        }

        return foundAmount >= neededAmount;
    }

    // ? (#1) : If the purchase orders are processed immediately, do we need to check for duplicates?
    public boolean verifyPurchaseOrders(ComponentType targetType, int neededAmount) {
        int foundAmount = 0;

        for (PurchaseOrder currentPurchaseOrder : purchaseOrderHistory) {
            if (currentPurchaseOrder.getPurchasedComponentType() == targetType) {
                foundAmount += currentPurchaseOrder.getPurchasedAmount();
            }
        }

        return foundAmount >= neededAmount;
    }

    // ? (#1) : ...
    public boolean newPurchaseOrder(ComponentType componentType, int neededAmount, double unitaryValue) {
        int foundAmount = 0;

        for (PurchaseOrder currentPurchaseOrder : purchaseOrderHistory) {
            if (currentPurchaseOrder.getPurchasedComponentType() == componentType) {
                foundAmount += currentPurchaseOrder.getPurchasedAmount();
            }
        }

        if (foundAmount < neededAmount) {
            purchaseOrderHistory.add(new PurchaseOrder(componentType, neededAmount, unitaryValue));
            return true;
        }

        return false;
    }

    public static List<Component> getAvailableComponents() {
        return availableComponents;
    }

    public static List<PurchaseOrder> getPurchaseOrderHistory() {
        return purchaseOrderHistory;
    }
}
