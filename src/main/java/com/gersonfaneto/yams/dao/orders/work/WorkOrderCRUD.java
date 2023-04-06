package com.gersonfaneto.yams.dao.orders.work;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.util.List;

public interface WorkOrderCRUD extends CRUD<WorkOrder> {

  List<WorkOrder> findByClient(String clientID);

  List<WorkOrder> findByTechnician(String technicianID);
}
