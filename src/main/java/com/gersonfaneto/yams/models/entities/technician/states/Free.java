package com.gersonfaneto.yams.models.entities.technician.states;

import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.states.Open;

/**
 * A concrete implementation of the <code>State</code> Class, representing the Free State of the
 * Technicians, that is, when they don't have any open <code>WorkOrder</code>.
 *
 * @see Technician
 * @see WorkOrder
 * @see com.gersonfaneto.yams.models.entities.technician.states.State;
 */
public class Free extends State {

  /**
   * Constructs a new <code>State</code> for a <code>Technician</code>.
   *
   * @param technician The targeted <code>Technician</code>.
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   */
  public Free(Technician technician) {
    super(technician, null);
  }

  /**
   * Attempts to open a <code>WorkOrder</code>.
   *
   * @param workOrder The targeted <code>WorkOrder</code>.
   * @return <code>true</code> if the <code>WorkOrder</code> hadn't been opened yet, or
   * <code>false</code>, if it was.
   */
  @Override
  public boolean openOrder(WorkOrder workOrder) {
    if (workOrder.getTechnicianID() == null) {
      workOrder.setTechnicianID(getTechnician().getUserID());
      workOrder.setWorkOrderState(new Open(workOrder));
      getTechnician().setTechnicianState(new Occupied(getTechnician(), workOrder));
      return true;
    }

    return false;
  }

  /**
   * Attempts to close a <code>WorkOrder</code>.
   *
   * @return Always <code>false</code>, as a <code>Technician</code> in this <code>State</code>
   * doesn't have any opened <code>WorkOrder</code>s.
   */
  @Override
  public boolean cancelOrder() {
    return false;
  }

  /**
   * Attempts to cancel a <code>WorkOrder</code>.
   *
   * @return Always <code>false</code>, as a <code>Technician</code> in this <code>State</code>
   * doesn't have any opened <code>WorkOrder</code>s.
   */
  @Override
  public boolean closeOrder() {
    return false;
  }

  /**
   * Attempts to generate a <code>Invoice</code> for the <code>WorkOrder</code>.
   *
   * @return Always <code>null</code>, as a <code>Technician</code> in this <code>State</code>
   * doesn't have any opened <code>WorkOrder</code>s.
   */
  @Override
  public Invoice generateInvoice() {
    return null;
  }
}
