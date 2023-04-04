package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Open extends State {
    public Open(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public boolean addService(String technicianID, Service chosenService) {
        if (technicianID.equals(getWorkOrder().getTechnicianID())) {
            chosenService.setWorkOrderID(getWorkOrder().getWorkOrderID());
            DAO.fromService().createOne(chosenService);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeService(String technicianID, Service chosenService) {
        if (technicianID.equals(getWorkOrder().getTechnicianID())) {
            return DAO.fromService().deleteByID(chosenService.getServiceID());
        }

        return false;
    }

    @Override
    public boolean generateInvoice(String technicianID) {
        return false;
    }
}
