package com.gersonfaneto.yams.dao.services;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Implementations for all the <code>ServiceCRUD</code> and <code>CRUD</code> operations. Uses a
 * <code>HashMap</code> to store all the <code>Service</code>s.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see ServiceCRUD
 */
public class ServiceMemoryDAO implements ServiceCRUD {

  private final Map<String, Service> storedServices;

  /**
   * Constructs a new <code>{@link ServiceMemoryDAO}</code>
   */
  public ServiceMemoryDAO() {
    this.storedServices = new HashMap<>();
  }

  @Override
  public Service createOne(Service newService) {
    String newID = Generators.randomID();

    newService.setServiceID(newID);

    storedServices.put(newID, newService);

    return newService;
  }

  @Override
  public Service findByID(String targetID) {
    return storedServices.get(targetID);
  }

  @Override
  public List<Service> findMany() {
    return storedServices.values()
        .stream()
        .toList();
  }

  @Override
  public boolean updateInformation(Service updatedService) {
    String serviceID = updatedService.getServiceID();

    if (storedServices.containsKey(serviceID)) {
      storedServices.put(serviceID, updatedService);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedServices.containsKey(targetID)) {
      storedServices.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedServices.isEmpty()) {
      storedServices.clear();
      return true;
    }

    return false;
  }

  @Override
  public List<Service> findByType(ServiceType serviceType) {
    return storedServices.values()
        .stream()
        .filter(x -> x.getServiceType().equals(serviceType))
        .toList();
  }

  @Override
  public List<Service> findByWorkOrder(String workOrderID) {
    return storedServices.values()
        .stream()
        .filter(x -> Objects.nonNull(x.getWorkOrderID()))
        .filter(x -> x.getWorkOrderID().equals(workOrderID))
        .toList();
  }
}
