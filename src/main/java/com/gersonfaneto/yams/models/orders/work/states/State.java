package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.billing.Invoice;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.work.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

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
