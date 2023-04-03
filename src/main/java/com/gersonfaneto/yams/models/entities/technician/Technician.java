package com.gersonfaneto.yams.models.entities.technician;

import com.gersonfaneto.yams.models.entities.technician.states.Free;
import com.gersonfaneto.yams.models.entities.technician.states.State;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

import java.util.LinkedList;
import java.util.List;

public class Technician extends User {
    private String technicianName;
    private State technicianState;
    private List<WorkOrder> workOrders;

    public Technician(String userEmail, String userPassword, String technicianName) {
        super(userEmail, userPassword);
        this.technicianName = technicianName;
        this.technicianState = new Free(this);
        this.workOrders = new LinkedList<>();
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
        return getTechnicianState().openOrder(workOrder);
    }

    public boolean cancelOrder() {
        return getTechnicianState().cancelOrder();
    }

    public boolean closeOrder() {
        return getTechnicianState().closeOrder();
    }

    // TODO: Add Implementations.
    public boolean generateInvoice() {
        return true;
    }

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

    public List<WorkOrder> getWorkOrders() {
        return workOrders;
    }

    public void setWorkOrders(List<WorkOrder> workOrders) {
        this.workOrders = workOrders;
    }
}
