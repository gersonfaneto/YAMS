package com.gersonfaneto.techinfo.dao.purchaseorder;

import com.gersonfaneto.techinfo.models.stock.PurchaseOrder;
import com.gersonfaneto.techinfo.models.stock.component.ComponentType;

import java.util.*;

public class PurchaseOrderListCRUD implements PurchaseOrderDAO {
    private final Map<String, PurchaseOrder> registeredPurchaseOrders;

    public PurchaseOrderListCRUD() {
        this.registeredPurchaseOrders = new HashMap<>();
    }

    @Override
    public PurchaseOrder createOne(PurchaseOrder newPurchaseOrder) {
        String newID = UUID.randomUUID().toString();

        newPurchaseOrder.setPurchaseOrderID(newID);

        registeredPurchaseOrders.put(newID, newPurchaseOrder);

        return newPurchaseOrder;
    }

    @Override
    public PurchaseOrder findByID(String targetID) {
        return registeredPurchaseOrders.get(targetID);
    }

    @Override
    public List<PurchaseOrder> findMany() {
        List<PurchaseOrder> foundPurchaseOrders = new LinkedList<>();

        for (PurchaseOrder currentPurchaseOrder : registeredPurchaseOrders.values()) {
            foundPurchaseOrders.add(currentPurchaseOrder);
        }

        return foundPurchaseOrders;
    }

    @Override
    public boolean updateInformation(PurchaseOrder targetPurchaseOrder) {
        String purchaseOrderID = targetPurchaseOrder.getPurchaseOrderID();

        if (registeredPurchaseOrders.containsKey(purchaseOrderID)) {
            registeredPurchaseOrders.put(purchaseOrderID, targetPurchaseOrder);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredPurchaseOrders.containsKey(targetID)) {
            registeredPurchaseOrders.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredPurchaseOrders.isEmpty()) {
            registeredPurchaseOrders.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<PurchaseOrder> findByTechnician(String technicianID) {
        List<PurchaseOrder> foundPurchaseOrders = new LinkedList<>();

        for (PurchaseOrder currentPurchaseOrder : registeredPurchaseOrders.values()) {
            if (currentPurchaseOrder.getTechnicianID().equals(technicianID)) {
                foundPurchaseOrders.add(currentPurchaseOrder);
            }
        }

        return foundPurchaseOrders;
    }

    @Override
    public List<PurchaseOrder> findByComponentType(ComponentType targetType) {
        List<PurchaseOrder> foundPurchaseOrders = new LinkedList<>();

        for (PurchaseOrder currentPurchaseOrder : registeredPurchaseOrders.values()) {
            if (currentPurchaseOrder.getComponentType() == targetType) {
                foundPurchaseOrders.add(currentPurchaseOrder);
            }
        }

        return foundPurchaseOrders;
    }
}
