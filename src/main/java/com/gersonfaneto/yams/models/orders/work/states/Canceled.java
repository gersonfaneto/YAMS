package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Canceled extends State {

  public Canceled(WorkOrder workOrder) {
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
    return false;
  }
}
