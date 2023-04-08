package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public abstract class State {

  private WorkOrder workOrder;

  public State(WorkOrder workOrder) {
    this.workOrder = workOrder;
  }

  public abstract boolean addService(String technicianID, Service chosenService);

  public abstract boolean removeService(String technicianID, Service chosenServices);

  public abstract boolean generateInvoice(String technicianID);
  public abstract boolean generateReport(String technicianID);

  public WorkOrder getWorkOrder() {
    return workOrder;
  }

  public void setWorkOrder(WorkOrder workOrder) {
    this.workOrder = workOrder;
  }
}
