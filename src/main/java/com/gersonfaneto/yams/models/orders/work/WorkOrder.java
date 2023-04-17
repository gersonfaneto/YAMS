package com.gersonfaneto.yams.models.orders.work;

import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.orders.work.states.Created;
import com.gersonfaneto.yams.models.orders.work.states.State;
import com.gersonfaneto.yams.models.reports.WorkReport;
import com.gersonfaneto.yams.models.services.Service;
import java.util.Calendar;

/**
 * Represents the Work Orders that can be created by the Technicians of the Assistance to group the
 * Services requested by a Client, using the "State" pattern to encapsulate some of its mutable
 * behavior.
 *
 * @see com.gersonfaneto.yams.models.orders.work.states.State
 * @see Service
 */
public class WorkOrder {

  private String workOrderID;
  private String clientID;
  private String technicianID;
  private String invoiceID;
  private State workOrderState;
  private Calendar createdAt;
  private Calendar closedAt;
  private WorkReport workReport;

  /**
   * Constructs a new <code>WorkOrder</code> for a <code>Client</code>. The initial
   * <code>State</code> of the <code>WorkOrder</code> is <code>Created</code>.
   *
   * @param clientID The ID of the Client that requested the <code>Service</code>s.
   * @see Created
   */
  public WorkOrder(String clientID) {
    this.clientID = clientID;
    this.workOrderState = new Created(this);
    this.createdAt = Calendar.getInstance();
  }

  /**
   * Removes a <code>Service</code> from the <code>WorkOrder</code> depending on its current
   * <code>State</code>.
   *
   * @param serviceID The ID of the targeted <code>Service</code>.
   * @return <code>null</code> or the removed <code>Service</code>, depending on the current
   * <code>State</code> of the <code>WorkOrder</code>.
   * @see com.gersonfaneto.yams.models.orders.work.states.State
   * @see Service
   */
  public Service removeService(String serviceID) {
    return workOrderState.removeService(serviceID);
  }

  /**
   * Attempts to generate the <code>Invoice</code> for the <code>WorkOrder</code> depending on its
   * current <code>State</code>.
   *
   * @return <code>null</code> or the generated <code>Invoice</code>, depending on the current
   * <code>State</code> of the <code>WorkOrder</code>.
   * @see com.gersonfaneto.yams.models.orders.work.states.State
   * @see Invoice
   */
  public Invoice generateInvoice() {
    return workOrderState.generateInvoice();
  }

  /**
   * Attempts to generate the <code>WorkReport</code> for the <code>WorkOrder</code> depending on
   * its current <code>State</code>.
   *
   * @return <code>null</code> or the generated <code>WorkReport</code>, depending on the current
   * <code>State</code> of the <code>WorkOrder</code>.
   * @see com.gersonfaneto.yams.models.orders.work.states.State
   * @see WorkReport
   */
  public WorkReport generateReport() {
    return workOrderState.generateReport();
  }

  /**
   * Compares an <code>Object</code> to the <code>WorkOrder</code>.
   *
   * @param otherObject The <code>Object</code> to be compared to.
   * @return <code>true</code> if the objects match, or <code>false</code> if they don't.
   */
  @Override
  public boolean equals(Object otherObject) {
    // Checking if the Object passed isn't the WorkOrder itself.
    if (this == otherObject) {
      return true;
    }

    // Checking if the Object passed isn't null.
    if (otherObject == null) {
      return false;
    }

    // Checking if the Object passed is from the Class WorkOrder and casting it.
    if (!(otherObject instanceof WorkOrder otherWorkOrder)) {
      return false;
    }

    // Comparing by the IDs.
    return workOrderID.equals(otherWorkOrder.workOrderID);
  }

  /**
   * Generate a <code>String</code> from the most important information of the
   * <code>WorkOrder</code>.
   *
   * @return Relevant information about the <code>User</code>.
   */
  @Override
  public String toString() {
    return String.format("""
        ID: %s
        Client: %s
        Technician: %s
        Invoice: %s
        """, workOrderID, clientID, technicianID, invoiceID);
  }

  public String getWorkOrderID() {
    return workOrderID;
  }

  public void setWorkOrderID(String workOrderID) {
    this.workOrderID = workOrderID;
  }

  public String getClientID() {
    return clientID;
  }

  public void setClientID(String clientID) {
    this.clientID = clientID;
  }

  public String getTechnicianID() {
    return technicianID;
  }

  public void setTechnicianID(String technicianID) {
    this.technicianID = technicianID;
  }

  public String getInvoiceID() {
    return invoiceID;
  }

  public void setInvoiceID(String invoiceID) {
    this.invoiceID = invoiceID;
  }

  public State getWorkOrderState() {
    return workOrderState;
  }

  public void setWorkOrderState(State workOrderState) {
    this.workOrderState = workOrderState;
  }

  public Calendar getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Calendar createdAt) {
    this.createdAt = createdAt;
  }

  public Calendar getClosedAt() {
    return closedAt;
  }

  public void setClosedAt(Calendar closedAt) {
    this.closedAt = closedAt;
  }

  public WorkReport getWorkReport() {
    return workReport;
  }

  public void setWorkReport(WorkReport workReport) {
    this.workReport = workReport;
  }
}
