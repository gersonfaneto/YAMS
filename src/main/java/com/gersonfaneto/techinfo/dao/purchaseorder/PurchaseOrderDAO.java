package com.gersonfaneto.techinfo.dao.purchaseorder;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.stock.PurchaseOrder;
import com.gersonfaneto.techinfo.models.stock.component.ComponentType;

import java.util.List;

public interface PurchaseOrderDAO extends CRUD<PurchaseOrder> {
    List<PurchaseOrder> findByTechnician(String technicianID);

    List<PurchaseOrder> findByComponentType(ComponentType targetType);
}
