package com.gersonfaneto.yams.models.entities.technician.states;

import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.states.Canceled;
import com.gersonfaneto.yams.models.orders.work.states.Finished;

public class Occupied extends State {

  public Occupied(Technician technician, WorkOrder workOrder) {
    super(technician, workOrder);
  }

  @Override
  public boolean openOrder(WorkOrder workOrder) {
    return false;
  }

  @Override
  public boolean cancelOrder() {
    getWorkOrder().setWorkOrderState(new Canceled(getWorkOrder()));
    getTechnician().setTechnicianState(new Free(getTechnician()));

    return false;
  }

  @Override
  public boolean closeOrder() {
    getWorkOrder().setWorkOrderState(new Finished(getWorkOrder()));
    getTechnician().setTechnicianState(new Free(getTechnician()));

    return true;
  }

  @Override
  public boolean generateInvoice() {
    return getWorkOrder().generateInvoice(getWorkOrder().getTechnicianID());
  }
}