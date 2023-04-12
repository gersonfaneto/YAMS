package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

public class Open extends State {

  public Open(WorkOrder workOrder) {
    super(workOrder);
  }

  @Override
  public Service removeService(String serviceID) {
    Service removedService = DAO.fromService().findByID(serviceID);

    DAO.fromService().deleteByID(serviceID);

    return removedService;
  }

  @Override
  public Invoice generateInvoice() {
    return null;
  }

  @Override
  public WorkReport generateReport() {
    return null;
  }
}
