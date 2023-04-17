package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

/**
 * A concrete implementation of the <code>State</code> Class, representing the Payed State of the
 * WorkOrder, that is, when the <code>Invoice</code> total has been completely paid.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see WorkOrder
 * @see Invoice
 * @see com.gersonfaneto.yams.models.entities.technician.states.State;
 */
public class Payed extends State {

  /**
   * Constructs a new <code>State</code> for the <code>WorkOrder</code>.
   *
   * @param workOrder The targeted <code>WorkOrder</code>.
   */
  public Payed(WorkOrder workOrder) {
    super(workOrder);
  }

  /**
   * Attempts to remove a <code>Service</code> from the <code>WorkOrder</code>.
   *
   * @param serviceID The ID of the targeted <code>Service</code>.
   * @return Always <code>null</code>, as a <code>Service</code> can't be removed from the
   * <code>WorkOrder</code> once its finished and paid.
   */
  @Override
  public Service removeService(String serviceID) {
    return null;
  }

  /**
   * Attempts to generate a <code>Invoice</code> for the <code>WorkOrder</code>.
   *
   * @return Always <code>null</code>, as an <code>Invoice</code> can't be generated for a
   * <code>WorkOrder</code> once its already paid.
   */
  @Override
  public Invoice generateInvoice() {
    return null;
  }

  /**
   * Attempts to generate the <code>WorkReport</code> for the <code>WorkOrder</code>.
   *
   * @return The generated <code>WorkReport</code>.
   */
  @Override
  public WorkReport generateReport() {
    WorkReport workReport = new WorkReport(getWorkOrder());

    getWorkOrder().setWorkReport(workReport);

    return workReport;
  }
}
