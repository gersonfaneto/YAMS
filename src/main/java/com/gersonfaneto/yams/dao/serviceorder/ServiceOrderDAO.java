package com.gersonfaneto.techinfo.dao.serviceorder;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.work.serviceorder.ServiceOrder;
import com.gersonfaneto.techinfo.models.work.serviceorder.ServiceOrderStatus;

import java.util.List;
import java.util.Queue;

public interface ServiceOrderDAO extends CRUD<ServiceOrder> {
    List<ServiceOrder> findByClient(String clientID);

    List<ServiceOrder> findByTechnician(String technicianID);

    List<ServiceOrder> findByStatus(ServiceOrderStatus targetStatus);
    Queue<ServiceOrder> findAllNext();
    ServiceOrder deleteNext();
}
