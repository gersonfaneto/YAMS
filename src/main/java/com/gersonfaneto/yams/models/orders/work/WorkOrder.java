package com.gersonfaneto.yams.models.orders.work;

import com.gersonfaneto.yams.models.orders.work.states.Created;
import com.gersonfaneto.yams.models.orders.work.states.State;
import com.gersonfaneto.yams.models.services.Service;
import java.util.Calendar;
import java.util.List;

public class WorkOrder {

  private String workOrderID;
  private String clientID;
  private String technicianID;
  private String invoiceID;
  private State workOrderState;
  private Calendar createdAt;
  private Calendar closedAt;
  private List<Service> chosenServices;

  public WorkOrder(String clientID, List<Service> chosenServices) {
    this.clientID = clientID;
    this.workOrderState = new Created(this);
    this.createdAt = Calendar.getInstance();
    this.chosenServices = chosenServices;
  }

  public boolean removeService(String technicianID, Service chosenService) {
    return workOrderState.removeService(technicianID, chosenService);
  }

  public boolean generateInvoice(String technicianID) {
    return workOrderState.generateInvoice(technicianID);
  }

  public boolean generateReport(String technicianID) {
    return workOrderState.generateReport(technicianID);
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }

    if (!(otherObject instanceof WorkOrder otherWorkOrder)) {
      return false;
    }

    return workOrderID.equals(otherWorkOrder.workOrderID);
  }

  @Override
  public String toString() {
    return String.format("""
        ID: %s
        Client: %s
        Technician: %s
        Invoice: %s
        Status: %s
        """, workOrderID, clientID, technicianID, invoiceID, workOrderState.getClass().getName());
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

  public List<Service> getChosenServices() {
    return chosenServices;
  }

  public void setChosenServices(
      List<Service> chosenServices) {
    this.chosenServices = chosenServices;
  }
}
