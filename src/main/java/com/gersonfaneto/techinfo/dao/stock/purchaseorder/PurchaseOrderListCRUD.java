package com.gersonfaneto.techinfo.dao.stock.purchaseorder;

import com.gersonfaneto.techinfo.models.stock.ComponentType;
import com.gersonfaneto.techinfo.models.stock.PurchaseOrder;

import java.util.LinkedList;
import java.util.List;

public class PurchaseOrderListCRUD implements PurchaseOrderDAO {
    private List<PurchaseOrder> purchaseOrderList;
    private int referenceID;

    public PurchaseOrderListCRUD() {
        this.purchaseOrderList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public PurchaseOrder createOne(PurchaseOrder targetObject) {
        targetObject.setPurchaseOrderID(++referenceID);
        purchaseOrderList.add(targetObject);
        return targetObject;
    }

    @Override
    public PurchaseOrder findByID(int targetID) {
        for (PurchaseOrder currentPurchaseOrder : purchaseOrderList) {
            if (currentPurchaseOrder.getPurchaseOrderID() == targetID) {
                return currentPurchaseOrder;
            }
        }
        return null;
    }

    @Override
    public List<PurchaseOrder> findMany() {
        return purchaseOrderList;
    }

    @Override
    public boolean updateInformation(PurchaseOrder targetObject) {
        for (PurchaseOrder currentPurchaseOrder : purchaseOrderList) {
            if (currentPurchaseOrder.getPurchaseOrderID() == targetObject.getPurchaseOrderID()) {
                int targetIndex = purchaseOrderList.indexOf(currentPurchaseOrder);
                purchaseOrderList.set(targetIndex, targetObject);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (PurchaseOrder currentPurchaseOrder : purchaseOrderList) {
            if (currentPurchaseOrder.getPurchaseOrderID() == targetID) {
                purchaseOrderList.remove(currentPurchaseOrder);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!purchaseOrderList.isEmpty()) {
            purchaseOrderList.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<PurchaseOrder> findByTechnician(int technicianID) {
        List<PurchaseOrder> foundPurchaseOrders = new LinkedList<>();

        for (PurchaseOrder currentPurchaseOrder : purchaseOrderList) {
            if (currentPurchaseOrder.getTechnicianID() == technicianID) {
                foundPurchaseOrders.add(currentPurchaseOrder);
            }
        }

        return foundPurchaseOrders;
    }

    @Override
    public List<PurchaseOrder> findByComponentType(ComponentType targetType) {
        List<PurchaseOrder> foundPurchaseOrders = new LinkedList<>();

        for (PurchaseOrder currentPurchaseOrder : purchaseOrderList) {
            if (currentPurchaseOrder.getPurchasedComponentType() == targetType) {
                foundPurchaseOrders.add(currentPurchaseOrder);
            }
        }

        return foundPurchaseOrders;
    }
}
