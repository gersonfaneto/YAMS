package com.gersonfaneto.yams.dao.purchaseorder;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.stock.PurchaseOrder;
import com.gersonfaneto.yams.models.stock.component.ComponentType;

import java.util.List;

public interface PurchaseOrderDAO extends CRUD<PurchaseOrder> {
    List<PurchaseOrder> findByTechnician(String technicianID);

    List<PurchaseOrder> findByComponentType(ComponentType targetType);
}
