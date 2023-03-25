package com.gersonfaneto.techinfo.models.stock;

import com.gersonfaneto.techinfo.dao.DAO;

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

    public boolean hasComponent(ComponentType targetType) {
        for (Component currentComponent : availableComponents) {
            if (currentComponent.getComponentType() == targetType) {
                return true;
            }
        }

        return false;
    }

    public static List<Component> getAvailableComponents() {
        return DAO.getComponents().findMany();
    }

    public static List<PurchaseOrder> getPurchaseOrderHistory() {
        return DAO.getPurchaseOrders().findMany();
    }
}
