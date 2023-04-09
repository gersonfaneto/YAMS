package com.gersonfaneto.yams.models.entities.technician.states;

import com.gersonfaneto.yams.models.billing.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;

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
