package com.gersonfaneto.yams.dao.orders.purchase;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;

public interface PurchaseOrderCRUD extends CRUD<PurchaseOrder> {

  List<PurchaseOrder> findByType(ComponentType componentType);
}
