package com.gersonfaneto.yams.models.stock;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.stock.component.Component;
import com.gersonfaneto.yams.models.stock.component.ComponentType;

import java.util.List;

public class Stock {
    private static Stock singleInstance;

    private Stock() {
    }

    public static Stock retrieveStock() {
        if (singleInstance == null) {
            singleInstance = new Stock();
        }
        return singleInstance;
    }

    public boolean hasComponent(ComponentType targetType) {
        return DAO.fromComponents().findByType(targetType).size() > 0;
    }

    public List<Component> retrieveAvailableComponents() {
        return DAO.fromComponents().findMany();
    }

    public List<Component> retrieveAvailableComponents(ComponentType targetType) {
        return DAO.fromComponents().findByType(targetType);
    }

    public PurchaseOrder makePurchase(String technicianID, ComponentType componentType, String componentDescription, int boughtAmount, double unitaryPrice, double unitaryCost) {
        PurchaseOrder newPurchaseOrder = new PurchaseOrder(technicianID, componentType, componentDescription, boughtAmount, unitaryPrice, unitaryCost);

        return DAO.fromPurchaseOrders().createOne(newPurchaseOrder);
    }

    public PurchaseOrder makePurchase(String technicianID, ComponentType componentType, String componentDescription, int boughtAmount, double unitaryCost) {
        PurchaseOrder newPurchaseOrder = new PurchaseOrder(technicianID, componentType, componentDescription, boughtAmount, unitaryCost);

        return DAO.fromPurchaseOrders().createOne(newPurchaseOrder);
    }

    public void arrangeComponents(PurchaseOrder purchaseOrder) {
        for (int i = 0; i < purchaseOrder.getPurchasedAmount(); i++) {
            DAO.fromComponents().createOne(
                    new Component(
                            purchaseOrder.getComponentType(),
                            purchaseOrder.getComponentDescription(),
                            purchaseOrder.getUnitaryPrice(),
                            purchaseOrder.getUnitaryCost()
                    )
            );
        }
    }

}
