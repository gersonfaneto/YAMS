package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.work.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

public class Payed extends State {

  public Payed(WorkOrder workOrder) {
    super(workOrder);
  }

  @Override
  public boolean removeService(String technicianID, Service chosenServices) {
    return false;
  }

  @Override
  public boolean generateInvoice(String technicianID) {
    return false;
  }

  @Override
  public boolean generateReport(String technicianID) {
    WorkReport workReport = new WorkReport(getWorkOrder());
    getWorkOrder().setWorkReport(workReport);
    return true;
  }
}
