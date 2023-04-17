package com.gersonfaneto.yams.models.entities.technician.states;

import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;

/**
 * Represents the base aspects of the State of the Technicians from the Assistance, by determining
 * their basic capabilities/operations. See the implementations for more detail on each method
 * behavior.
 *
 * @see Technician
 * @see WorkOrder
 * @see Free
 * @see Occupied
 */
public abstract class State {

  private final Technician technician;
  private final WorkOrder workOrder;

  public State(Technician technician, WorkOrder workOrder) {
    this.technician = technician;
    this.workOrder = workOrder;
  }

  public abstract boolean openOrder(WorkOrder workOrder);

  public abstract boolean cancelOrder();

  public abstract boolean closeOrder();

  public abstract Invoice generateInvoice();

  public Technician getTechnician() {
    return technician;
  }

  public WorkOrder getWorkOrder() {
    return workOrder;
  }
}
