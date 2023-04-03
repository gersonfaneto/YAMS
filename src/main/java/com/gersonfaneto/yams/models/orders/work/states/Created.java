package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Created extends State {
    public Created(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public boolean addService(String technicianID, Service chosenService) {
        getWorkOrder().getSelectedServices().add(chosenService);
        return true;
    }

    @Override
    public boolean removeService(String technicianID, Service chosenServices) {
        getWorkOrder().getSelectedServices().remove(chosenServices);
        return true;
    }
}
