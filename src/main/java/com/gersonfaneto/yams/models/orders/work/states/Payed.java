package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.billing.Invoice;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.work.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

public class Payed extends State {

  public Payed(WorkOrder workOrder) {
    super(workOrder);
  }

  @Override
  public Service removeService(String serviceID) {
    return null;
  }

  @Override
  public Invoice generateInvoice() {
    return null;
  }

  @Override
  public WorkReport generateReport() {
    WorkReport workReport = new WorkReport(getWorkOrder());

    getWorkOrder().setWorkReport(workReport);

    return workReport;
  }
}
