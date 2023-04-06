package com.gersonfaneto.yams.dao.orders.work;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class WorkOrderMemoryDAO implements WorkOrderCRUD {

  private final Map<String, WorkOrder> storedWorkOrders;

  public WorkOrderMemoryDAO() {
    this.storedWorkOrders = new HashMap<>();
  }

  @Override
  public WorkOrder createOne(WorkOrder newWorkOrder) {
    String newID = UUID.randomUUID().toString();

    newWorkOrder.setWorkOrderID(newID);

    storedWorkOrders.put(newID, newWorkOrder);

    return newWorkOrder;
  }

  @Override
  public WorkOrder findByID(String targetID) {
    return storedWorkOrders.get(targetID);
  }

  @Override
  public List<WorkOrder> findMany() {
    return storedWorkOrders.values().stream().toList();
  }

  @Override
  public boolean updateInformation(WorkOrder updatedWorkOrder) {
    String workOrderID = updatedWorkOrder.getWorkOrderID();

    if (storedWorkOrders.containsKey(workOrderID)) {
      storedWorkOrders.put(workOrderID, updatedWorkOrder);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedWorkOrders.containsKey(targetID)) {
      storedWorkOrders.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedWorkOrders.isEmpty()) {
      storedWorkOrders.clear();
      return true;
    }

    return false;
  }

  @Override
  public List<WorkOrder> findByClient(String clientID) {
    return storedWorkOrders.values()
        .stream()
        .filter(x -> x.getClientID().equals(clientID))
        .toList();
  }

  @Override
  public List<WorkOrder> findByTechnician(String technicianID) {
    return storedWorkOrders.values()
        .stream()
        .filter(x -> x.getClientID().equals(technicianID))
        .toList();
  }
}
