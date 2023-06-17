package com.gersonfaneto.yams.models.services.order;

import java.io.Serializable;
import java.util.Calendar;

public class WorkOrder implements Serializable {

  private String workOrderID;
  private String clientID;
  private String technicianID;
  private String invoiceID;
  private WorkOrderState workOrderState;
  private Calendar createdAt;
  private Calendar closedAt;

  public WorkOrder(String clientID) {
    this.clientID = clientID;
    this.workOrderState = WorkOrderState.Created;
    this.createdAt = Calendar.getInstance();
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
   * Generate a <code>String</code> from the most important information of the <code>WorkOrder
   * </code>.
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

  public WorkOrderState getWorkOrderState() {
    return workOrderState;
  }

  public void setWorkOrderState(WorkOrderState workOrderState) {
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
}
