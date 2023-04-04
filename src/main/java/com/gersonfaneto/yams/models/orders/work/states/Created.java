package com.gersonfaneto.yams.models.orders.work.states;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;

public class Created extends State {
    public Created(WorkOrder workOrder) {
        super(workOrder);
    }

    @Override
    public boolean addService(String technicianID, Service chosenService) {
        chosenService.setWorkOrderID(getWorkOrder().getWorkOrderID());
        DAO.fromService().createOne(chosenService);
        return true;
    }

    @Override
    public boolean removeService(String technicianID, Service chosenServices) {
        return DAO.fromService().deleteByID(chosenServices.getServiceID());
    }

    @Override
    public boolean generateInvoice(String technicianID) {
        return false;
    }
}
