package com.gersonfaneto.yams.dao.orders.work;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Implementations for the <code>WorkOrderCRUD</code> and the <code>CRUD</code> operations. Uses a
 * <code>HashMap</code> to store all the <code>WorkOrder</code>s.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see WorkOrderCRUD
 */
public class WorkOrderMemoryDAO implements WorkOrderCRUD {

  private final Map<String, WorkOrder> storedWorkOrders;

  /**
   * Initializes the <code>HashMap</code> used to store all the <code>WorkOrder</code>s.
   */
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
        .filter(x -> x.getTechnicianID().equals(technicianID))
        .toList();
  }
}
