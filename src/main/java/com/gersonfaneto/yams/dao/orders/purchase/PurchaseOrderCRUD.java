package com.gersonfaneto.yams.dao.orders.purchase;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderCRUD extends CRUD<PurchaseOrder> {

  List<PurchaseOrder> findByType(String componentType);
}
