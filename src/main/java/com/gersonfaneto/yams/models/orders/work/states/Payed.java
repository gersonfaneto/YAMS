package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Payed extends State{
    public Payed(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public boolean addService(String technicianID, Service chosenService) {
        return false;
    }

    @Override
    public boolean removeService(String technicianID, Service chosenServices) {
        return false;
    }
}
