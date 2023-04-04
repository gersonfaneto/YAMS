package com.gersonfaneto.yams.models.entities.technician.states;

import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.states.Open;

public class Free extends State {
    public Free(Technician technician) {
        super(technician, null);
    }

    @Override
    public boolean openOrder(WorkOrder workOrder) {
        if (workOrder.getTechnicianID() == null) {
            workOrder.setTechnicianID(getTechnician().getUserID());
            workOrder.setWorkOrderState(new Open(workOrder));
            getTechnician().setTechnicianState(new Occupied(getTechnician(), workOrder));
            return true;
        }

        return false;
    }

    @Override
    public boolean cancelOrder() {
        return false;
    }

    @Override
    public boolean closeOrder() {
        return false;
    }
}
