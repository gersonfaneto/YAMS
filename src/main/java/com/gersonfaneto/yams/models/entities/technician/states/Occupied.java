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

/**
 * A concrete implementation of the <code>State</code> Class, representing the Occupied State of the
 * Technicians, that is, when they have an open <code>WorkOrder</code>.
 *
 * @see Technician
 * @see WorkOrder
 * @see com.gersonfaneto.yams.models.entities.technician.states.State
 */
public class Occupied extends State {

  /**
   * Constructs a new <code>State</code> for a <code>Technician</code>.
   *
   * @param technician The targeted <code>Technician</code>.
   * @param workOrder  The targeted <code>WorkOrder</code>.
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   */
  public Occupied(Technician technician, WorkOrder workOrder) {
    super(technician, workOrder);
  }

  /**
   * Attempts to open a new <code>WorkOrder</code>.
   *
   * @param workOrder The targeted <code>WorkOrder</code>.
   * @return Always <code>false</code>, as the <code>Technician</code> can't have multiple
   * <code>WorkOrder</code>s open at the same time.
   */
  @Override
  public boolean openOrder(WorkOrder workOrder) {
    return false;
  }

  /**
   * Attempts to close the current <code>WorkOrder</code>.
   *
   * @return <code>true</code> if all the <code>Service</code>s related to the
   * <code>WorkOrder</code> were completed, or <code>false</code>, if there is still at least one
   * <code>Service</code> pending.
   */
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

  /**
   * Attempts to close the current <code>WorkOrder</code>.
   *
   * @return <code>true</code> if all the <code>Service</code>s related to the
   * <code>WorkOrder</code> were completed, or <code>false</code>, if there is still at least one
   * <code>Service</code> pending.
   */
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

  /**
   * Attempts to generate a <code>Invoice</code> for the <code>WorkOrder</code>.
   *
   * @return <code>true</code> if the <code>WorkOrder</code> is finished, or <code>false</code>, if
   * it wasn't.
   * @see com.gersonfaneto.yams.models.orders.work.states.State
   */
  @Override
  public Invoice generateInvoice() {
    return getWorkOrder().generateInvoice();
  }
}
