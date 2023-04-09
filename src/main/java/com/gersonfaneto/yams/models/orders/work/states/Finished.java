package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.Invoice;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Finished extends State {

  public Finished(WorkOrder workOrder) {
    super(workOrder);
  }

  @Override
  public boolean removeService(String technicianID, Service chosenServices) {
    return false;
  }

  @Override
  public boolean generateInvoice(String technicianID) {
    DAO.fromInvoices()
        .createOne(
            new Invoice(
                getWorkOrder().getWorkOrderID(),
                getWorkOrder().getChosenServices().stream()
                    .map(Service::getServicePrice)
                    .reduce(0.0, Double::sum)));

    return true;
  }

  @Override
  public boolean generateReport(String technicianID) {
    return false;
  }
}
