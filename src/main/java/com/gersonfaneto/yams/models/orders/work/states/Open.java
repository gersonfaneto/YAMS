package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.reports.WorkReport;
import com.gersonfaneto.yams.models.services.Service;

/**
 * A concrete implementation of the <code>State</code> Class, representing the Open State of the
 * WorkOrder, that is, when a <code>Technician</code> is taking care of it.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see Technician
 * @see WorkOrder
 * @see Invoice
 * @see com.gersonfaneto.yams.models.entities.technician.states.State;
 */
public class Open extends State {

  /**
   * Constructs a new <code>State</code> for the <code>WorkOrder</code>.
   *
   * @param workOrder The targeted <code>WorkOrder</code>.
   */
  public Open(WorkOrder workOrder) {
    super(workOrder);
  }

  /**
   * Attempts to remove a <code>Service</code> from the <code>WorkOrder</code>.
   *
   * @param serviceID The ID of the targeted <code>Service</code>.
   * @return The <code>Service</code> itself if it was found, or <code>null</code>, if it wasn't.
   */
  @Override
  public Service removeService(String serviceID) {
    Service removedService = DAO.fromService().findByID(serviceID);

    DAO.fromService().deleteByID(serviceID);

    return removedService;
  }

  /**
   * Attempts to generate a <code>Invoice</code> for the <code>WorkOrder</code>.
   *
   * @return Always <code>null</code>, as an <code>Invoice</code> can't be generated for a <code>
   *     WorkOrder</code> that isn't finished yet.
   */
  @Override
  public Invoice generateInvoice() {
    return null;
  }

  /**
   * Attempts to generate the <code>WorkReport</code> for the <code>WorkOrder</code>.
   *
   * @return Always <code>null</code>, as a <code>WorkReport</code> can't be generated for a <code>
   *     WorkOrder</code> that isn't finished yet.
   */
  @Override
  public WorkReport generateReport() {
    return null;
  }
}
