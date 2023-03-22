package com.gersonfaneto.techinfo.models.stock;

import java.util.LinkedList;
import java.util.List;

public class Stock {
    private static Stock singleInstance = null;
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

    public Boolean verifyComponents() {
        return true;
    }

    public Boolean verifyPurchaseOrders() {
        return true;
    }

    public Boolean newPurchaseOrder() {
        return true;
    }
}
