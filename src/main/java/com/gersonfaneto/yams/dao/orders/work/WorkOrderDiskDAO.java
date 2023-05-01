package com.gersonfaneto.yams.dao.orders.work;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.ObjectIO;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>WorkOrderCRUD</code> and <code>CRUD</code> operations. Uses a
 * <code> HashMap</code> as a cache to store all the <code>WorkOrder</code>s during the
 * execution of the program and loads or unloads the contents of it into a file using an
 * <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see WorkOrderCRUD
 * @see ObjectIO
 */
public class WorkOrderDiskDAO implements WorkOrderCRUD {

  private final Map<String, WorkOrder> storedWorkOrders;
  private final ObjectIO<WorkOrder> workOrderObjectIO;

  /**
   * Constructs a new <code>{@link WorkOrderDiskDAO}</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public WorkOrderDiskDAO(String savePath) {
    this.storedWorkOrders = new HashMap<>();
    this.workOrderObjectIO = new ObjectIO<>(savePath);
  }

  public boolean saveAll() {
    List<WorkOrder> toSave = storedWorkOrders.values()
        .stream()
        .toList();

    return workOrderObjectIO.saveObjects(toSave);
  }

  public boolean loadAll() {
    List<WorkOrder> loadedWorkOrders = workOrderObjectIO.loadObjects();

    if (loadedWorkOrders == null) {
      return false;
    }

    for (WorkOrder currentWorkOrder : loadedWorkOrders) {
      storedWorkOrders.put(currentWorkOrder.getWorkOrderID(), currentWorkOrder);
    }

    return true;
  }

  @Override
  public WorkOrder createOne(WorkOrder newWorkOrder) {
    String newID = Generators.randomID();

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
    return storedWorkOrders.values()
        .stream()
        .toList();
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
