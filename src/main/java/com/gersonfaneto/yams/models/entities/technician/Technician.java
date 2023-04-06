package com.gersonfaneto.yams.models.entities.technician;

import com.gersonfaneto.yams.models.entities.technician.states.Free;
import com.gersonfaneto.yams.models.entities.technician.states.State;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Technician extends User {

  private String technicianName;
  private State technicianState;

  public Technician(String userEmail, String userPassword, String technicianName) {
    super(userEmail, userPassword);
    this.technicianName = technicianName;
    this.technicianState = new Free(this);
  }

  public WorkOrder createWorkOrder(String clientID) {
    return new WorkOrder(clientID);
  }

  public boolean addService(WorkOrder workOrder, Service chosenService) {
    return workOrder.addService(getUserID(), chosenService);
  }

  public boolean removeService(WorkOrder workOrder, Service chosenService) {
    return workOrder.removeService(getUserID(), chosenService);
  }

  public boolean openOrder(WorkOrder workOrder) {
    return technicianState.openOrder(workOrder);
  }

  public boolean cancelOrder() {
    return technicianState.cancelOrder();
  }

  public boolean closeOrder() {
    return technicianState.closeOrder();
  }

  public boolean generateInvoice() {
    return technicianState.generateInvoice();
  }

  // TODO: Add Implementations.
  public boolean buyComponent() {
    return true;
  }

  public boolean generateReport() {
    return true;
  }

  public String getTechnicianName() {
    return technicianName;
  }

  public void setTechnicianName(String technicianName) {
    this.technicianName = technicianName;
  }

  public State getTechnicianState() {
    return technicianState;
  }

  public void setTechnicianState(State technicianState) {
    this.technicianState = technicianState;
  }
}
