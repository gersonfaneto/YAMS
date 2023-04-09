package com.gersonfaneto.yams.dao.services;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import java.util.List;

public interface ServiceCRUD extends CRUD<Service> {

  List<Service> findByType(ServiceType serviceType);
  List<Service> findByWorkOrder(String workOrderID);
}
