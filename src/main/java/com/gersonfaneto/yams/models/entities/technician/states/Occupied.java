package com.gersonfaneto.yams.models.entities.technician.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.orders.UnsupportedOperationException;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.states.Canceled;
import com.gersonfaneto.yams.models.orders.work.states.Finished;
import com.gersonfaneto.yams.models.services.Service;
import java.util.Calendar;
import java.util.List;

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
    List<Service> relatedServices = DAO.fromService()
        .findByWorkOrder(getWorkOrder().getWorkOrderID());

    for (Service currentService : relatedServices) {
      if (!currentService.isComplete()) {
        return false;
      }
    }

    getWorkOrder().setClosedAt(Calendar.getInstance());
    getWorkOrder().setWorkOrderState(new Canceled(getWorkOrder()));
    getTechnician().setTechnicianState(new Free(getTechnician()));

    return true;
  }

  @Override
  public boolean closeOrder() {
    List<Service> relatedServices = DAO.fromService()
        .findByWorkOrder(getWorkOrder().getWorkOrderID());

    for (Service currentService : relatedServices) {
      if (!currentService.isComplete()) {
        return false;
      }
    }

    getWorkOrder().setClosedAt(Calendar.getInstance());
    getWorkOrder().setWorkOrderState(new Finished(getWorkOrder()));
    getTechnician().setTechnicianState(new Free(getTechnician()));

    return true;
  }

  @Override
  public Invoice generateInvoice() {
    return getWorkOrder().generateInvoice();
  }
}
