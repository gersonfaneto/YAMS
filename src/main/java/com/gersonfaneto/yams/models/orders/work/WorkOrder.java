package com.gersonfaneto.yams.models.orders.work;

import com.gersonfaneto.yams.models.orders.work.states.Created;
import com.gersonfaneto.yams.models.orders.work.states.State;
import com.gersonfaneto.yams.models.services.Service;

import java.util.LinkedList;
import java.util.List;

public class WorkOrder {
    private String workOrderID;
    private String clientID;
    private String technicianID;
    private String invoiceID;
    private State workOrderState;
    private List<Service> selectedServices;

    public WorkOrder(String clientID) {
        this.clientID = clientID;
        this.workOrderState = new Created(this);
        this.selectedServices = new LinkedList<>();
    }

    public boolean addService(String technicianID, Service chosenService) {
        return workOrderState.addService(technicianID, chosenService);
    }

    public boolean removeService(String technicianID,Service chosenService) {
        return workOrderState.removeService(technicianID, chosenService);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof WorkOrder otherWorkOrder) {
            return otherWorkOrder.workOrderID.equals(this.workOrderID);
        }

        return false;
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

    public List<Service> getSelectedServices() {
        return selectedServices;
    }

    public void setSelectedServices(List<Service> selectedServices) {
        this.selectedServices = selectedServices;
    }
}
