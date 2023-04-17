package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

/**
 * A concrete implementation of the <code>State</code> Class, representing the Finished State of the
 * WorkOrder, that is, when all its <code>Service</code>s have been completed.
 *
 * @see Technician
 * @see WorkOrder
 * @see Invoice
 * @see com.gersonfaneto.yams.models.entities.technician.states.State;
 */
public class Finished extends State {

  /**
   * Constructs a new <code>State</code> for the <code>WorkOrder</code>.
   *
   * @param workOrder The targeted <code>WorkOrder</code>.
   */
  public Finished(WorkOrder workOrder) {
    super(workOrder);
  }

  /**
   * Attempts to remove a <code>Service</code> from the <code>WorkOrder</code>.
   *
   * @param serviceID The ID of the targeted <code>Service</code>.
   * @return Always <code>null</code>, as a <code>Service</code> can't be removed from the
   * <code>WorkOrder</code> once its finished.
   */
  @Override
  public Service removeService(String serviceID) {
    return null;
  }

  /**
   * Attempts to generate a <code>Invoice</code> for the <code>WorkOrder</code>.
   *
   * @return The generated <code>Invoice</code> for the <code>WorkOrder</code>.
   */
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

  /**
   * Attempts to generate the <code>WorkReport</code> for the <code>WorkOrder</code>.
   *
   * @return Always <code>null</code>, as a <code>WorkReport</code> can't be generated for a
   * <code>WorkOrder</code> hasn't been paid yet.
   */
  @Override
  public WorkReport generateReport() {
    return null;
  }
}
