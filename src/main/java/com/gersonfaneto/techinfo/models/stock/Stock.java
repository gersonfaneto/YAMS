package com.gersonfaneto.techinfo.models.stock;

import java.util.LinkedList;
import java.util.List;

public class Stock {
    private Stock singleInstance = null;
    private List<Component> availableComponents;
    private List<PurchaseOrder> purchaseOrderHistory;

    private Stock() {
    }

    public Stock retrieveStock() {
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

    public List<Component> getAvailableComponents() {
        return availableComponents;
    }

    public List<PurchaseOrder> getPurchaseOrderHistory() {
        return purchaseOrderHistory;
    }
}
