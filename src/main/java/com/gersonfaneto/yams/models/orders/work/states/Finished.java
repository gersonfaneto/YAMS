package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

public class Finished extends State {

  public Finished(WorkOrder workOrder) {
    super(workOrder);
  }

  @Override
  public Service removeService(String serviceID) {
    return null;
  }

  @Override
  public Invoice generateInvoice() {
    double totalValue = DAO.fromService()
        .findByWorkOrder(getWorkOrder().getWorkOrderID())
        .stream()
        .map(Service::getServicePrice)
        .reduce(0.0, Double::sum);

    return DAO.fromInvoices().createOne(
        new Invoice(getWorkOrder().getWorkOrderID(), totalValue)
    );
  }

  @Override
  public WorkReport generateReport() {
    return null;
  }
}
