package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.technician.states.Free;
import com.gersonfaneto.yams.models.entities.technician.states.Occupied;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

/**
 * Represents the base aspects of the State of the WorkOrders of the Assistance, by determining
 * their basic capabilities/operations. See the implementations for more detail on each method
 * behavior.
 *
 * @see WorkOrder
 * @see Invoice
 * @see WorkReport
 * @see Created
 * @see Open
 * @see Canceled
 * @see Finished
 * @see Payed
 */
public abstract class State {

  private final WorkOrder workOrder;

  public State(WorkOrder workOrder) {
    this.workOrder = workOrder;
  }

  public abstract Service removeService(String serviceID);

  public abstract Invoice generateInvoice();

  public abstract WorkReport generateReport();

  public WorkOrder getWorkOrder() {
    return workOrder;
  }
}
