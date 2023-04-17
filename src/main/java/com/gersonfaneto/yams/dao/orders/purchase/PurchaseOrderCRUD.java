package com.gersonfaneto.yams.dao.orders.purchase;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;

/**
 * Extends the <code>CRUD</code> interface by adding operations specific to the
 * <code>PurchaseOrder</code> models.
 *
 * @see CRUD
 * @see PurchaseOrder
 */
public interface PurchaseOrderCRUD extends CRUD<PurchaseOrder> {

  /**
   * Searches for all the <code>PurchaseOrders</code> for a given <code>Component</code> type.
   *
   * @param componentType The targeted <code>Component</code> type.
   * @return The list of all the found <code>PurchaseOrder</code>s.
   */
  List<PurchaseOrder> findByType(ComponentType componentType);
}
