package com.gersonfaneto.yams.dao.services.service;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.ObjectIO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Implementations for the <code>ServiceCRUD</code> and <code>CRUD</code> operations. Uses a
 * <code> HashMap</code> as a cache to store all the <code>Service</code>s during the
 * execution of the program and loads or unloads the contents of it into a file using an
 * <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see ServiceCRUD
 * @see ObjectIO
 */
public class ServiceDiskDAO implements ServiceCRUD, Persist {

  private final Map<String, Service> storedServices;
  private final ObjectIO<Service> serviceObjectIO;

  /**
   * Constructs a new <code>{@link ServiceDiskDAO}</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public ServiceDiskDAO(String savePath) {
    this.storedServices = new HashMap<>();
    this.serviceObjectIO = new ObjectIO<>(savePath);
  }

  public boolean saveAll() {
    List<Service> toSave = storedServices.values()
        .stream()
        .toList();

    return serviceObjectIO.saveObjects(toSave);
  }

  public boolean loadAll() {
    List<Service> loadedServices = serviceObjectIO.loadObjects();

    if (loadedServices == null) {
      return false;
    }

    for (Service currentService : loadedServices) {
      storedServices.put(currentService.getServiceID(), currentService);
    }

    return true;
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
