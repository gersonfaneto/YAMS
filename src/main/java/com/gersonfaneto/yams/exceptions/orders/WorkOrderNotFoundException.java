package com.gersonfaneto.yams.exceptions.orders;

import com.gersonfaneto.yams.dao.orders.work.WorkOrderCRUD;
import com.gersonfaneto.yams.dao.orders.work.WorkOrderMemoryDAO;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;

/**
 * Thrown when a <code>WorkOrder</code> isn't found by the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see WorkOrderMemoryDAO
 * @see WorkOrderCRUD
 * @see WorkOrder
 */
public class WorkOrderNotFoundException extends Exception {

  /**
   * Constructs a new <code>WorkOrderNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public WorkOrderNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
