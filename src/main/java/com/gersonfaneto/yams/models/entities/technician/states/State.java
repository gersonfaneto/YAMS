package com.gersonfaneto.yams.models.entities.technician.states;

import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;

public abstract class State {
    private Technician technician;
    private WorkOrder workOrder;

    public State(Technician technician, WorkOrder workOrder) {
        this.technician = technician;
        this.workOrder = workOrder;
    }

    public abstract boolean openOrder(WorkOrder workOrder);

    public abstract boolean cancelOrder();

    public abstract boolean closeOrder();

    public Technician getTechnician() {
        return technician;
    }

    public void setTechnician(Technician technician) {
        this.technician = technician;
    }

    public WorkOrder getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(WorkOrder workOrder) {
        this.workOrder = workOrder;
    }
}
