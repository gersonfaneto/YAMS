package com.gersonfaneto.techinfo.dao.stock.purchaseorder;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.stock.ComponentType;
import com.gersonfaneto.techinfo.models.stock.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderDAO extends CRUD<PurchaseOrder> {
    public List<PurchaseOrder> findByTechnician(int technicianID);

    public List<PurchaseOrder> findByComponentType(ComponentType targetType);
}
