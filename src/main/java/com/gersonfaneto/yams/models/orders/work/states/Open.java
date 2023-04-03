package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Open extends State {
    public Open(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public boolean addService(String technicianID, Service chosenService) {
        if (technicianID.equals(getWorkOrder().getTechnicianID())) {
            getWorkOrder().getSelectedServices().add(chosenService);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeService(String technicianID, Service chosenServices) {
        if (technicianID.equals(getWorkOrder().getTechnicianID())) {
            getWorkOrder().getSelectedServices().remove(chosenServices);
            return true;
        }

        return false;
    }
}
