package com.gersonfaneto.yams.dao.services.order;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.services.order.WorkOrder;

import java.util.List;

/**
 * Extends the <code>CRUD</code> interface by adding operations specific to the <code>WorkOrder
 * </code> models.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see WorkOrder
 */
public interface WorkOrderCRUD extends CRUD<WorkOrder> {

  /**
   * Searches for all the <code>WorkOrder</code>s related to a given <code>Client</code>.
   *
   * @param clientID The ID of the targeted <code>Client</code>.
   * @return The list of all the found <code>WorkOrder</code>s.
   */
  List<WorkOrder> findByClient(String clientID);

  /**
   * Searches for all the <code>WorkOrder</code>s related to a given <code>Technician</code>.
   *
   * @param technicianID The ID of the targeted <code>Technician</code>.
   * @return The list of all the found <code>WorkOrder</code>s.
   */
  List<WorkOrder> findByTechnician(String technicianID);
}
