package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.orders.work.WorkOrderCRUD;
import com.gersonfaneto.yams.dao.orders.work.WorkOrderMemoryDAO;
import com.gersonfaneto.yams.dao.services.ServiceCRUD;
import com.gersonfaneto.yams.dao.services.ServiceMemoryDAO;
import com.gersonfaneto.yams.exceptions.client.ClientNotFoundException;
import com.gersonfaneto.yams.exceptions.orders.UnsupportedOperationException;
import com.gersonfaneto.yams.exceptions.orders.WorkOrderNotFoundException;
import com.gersonfaneto.yams.exceptions.services.ServiceNotFoundException;
import com.gersonfaneto.yams.exceptions.services.ServiceTypeNotFoundException;
import com.gersonfaneto.yams.exceptions.users.UserNotFoundException;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.states.Created;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import com.gersonfaneto.yams.models.stock.Component;
import java.util.Comparator;
import java.util.List;

/**
 * Controller containing all the operations related with the <code>Service</code> and <code>
 * WorkOrder</code> models on the System, such as creating, updating and retrieving information
 * about them. Most of the operations relly on the CRUD operations accessed through the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see ServiceMemoryDAO
 * @see ServiceCRUD
 * @see Service
 * @see WorkOrderMemoryDAO
 * @see WorkOrderCRUD
 * @see WorkOrder
 */
public abstract class ServicesController {

  /**
   * Generates a new <code>WorkOrder</code> with the <code>Service</code>s selected by the <code>
   * Client</code>.
   *
   * @param clientID The ID of the <code>Client</code>..
   * @param chosenServices A list of <code>Service</code>s selected by the <code>Client</code>.
   * @return The generated <code>WorkOrder</code>.
   * @throws ClientNotFoundException If the <code>clientID</code> doesn't match any of the ones from
   *     the registered <code>Client</code>s.
   * @see Client
   * @see WorkOrder
   */
  public static WorkOrder createWorkOrder(String clientID, List<Service> chosenServices)
      throws ClientNotFoundException {
    Client foundClient = DAO.fromClients().findByID(clientID);

    if (foundClient == null) {
      throw new ClientNotFoundException("Client not found!");
    }

    WorkOrder workOrder = new WorkOrder(foundClient.getClientID());

    for (Service currentService : chosenServices) {
      currentService.setWorkOrderID(workOrder.getWorkOrderID());
      DAO.fromService().updateInformation(currentService);
    }

    return DAO.fromWorkOrders().createOne(workOrder);
  }

  /**
   * Creates a "Queue" of the <code>WorkOrder</code>s on the System, by selecting only the ones at
   * the <code>Created</code> <code>State</code> and sorting them by time of creation.
   *
   * @return A list of the found <code>WorkOrder</code>s.
   * @see WorkOrder
   */
  public static List<WorkOrder> listWorkOrders() {
    return DAO.fromWorkOrders().findMany().stream()
        .filter(x -> x.getWorkOrderState() instanceof Created)
        .min(Comparator.comparing(WorkOrder::getCreatedAt))
        .stream()
        .toList();
  }

  /**
   * Generates a new <code>Service</code> requested by the <code>Client</code>.
   *
   * @param serviceType The type of the <code>Service</code>.
   * @param serviceDescription The description of the <code>Service</code>.
   * @param usedComponents The list of the <code>Component</code>s to be used in the <code>Service
   *     </code>.
   * @return The generated <code>Service</code>.
   * @throws ServiceTypeNotFoundException If the <code>serviceType</code> doesn't match any of the
   *     ones declared on the <code>ServiceType</code> enumeration.
   * @see Service
   * @see ServiceType
   */
  public static Service createService(
      String serviceType, String serviceDescription, List<Component> usedComponents)
      throws ServiceTypeNotFoundException {
    if (ServiceType.findByName(serviceType) == null) {
      throw new ServiceTypeNotFoundException("Service type not found!");
    }

    Service newService =
        new Service(ServiceType.findByName(serviceType), serviceDescription, usedComponents);

    return DAO.fromService().createOne(newService);
  }

  /**
   * Attempts to remove a <code>Service</code> from a <code>WorkOrder</code>.
   *
   * @param workOrderID The ID of the targeted <code>WorkOrder</code>.
   * @param serviceID The ID of the targeted <code>Service</code>.
   * @return The removed <code>Service</code>.
   * @throws ServiceNotFoundException If the <code>serviceID</code> doesn't match any of the ones
   *     from the registered <code>Service</code>s.
   * @throws WorkOrderNotFoundException If the <code>workOrderID</code> doesn't match any of the
   *     ones from the registered <code>WorkOrders</code>.
   * @throws UnsupportedOperationException If the targeted <code>Service</code> was already
   *     completed or the current <code>State</code> of the <code>WorkOrder</code> doesn't allow the
   *     removal of it.
   * @see Service
   * @see WorkOrder
   * @see com.gersonfaneto.yams.models.orders.work.states.State
   */
  public static Service removeService(String workOrderID, String serviceID)
      throws ServiceNotFoundException, WorkOrderNotFoundException, UnsupportedOperationException {
    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(workOrderID);
    Service foundService = DAO.fromService().findByID(serviceID);

    if (foundWorkOrder == null) {
      throw new WorkOrderNotFoundException("Order not found!");
    }

    if (foundService == null) {
      throw new ServiceNotFoundException("Service not found!");
    }

    if (foundService.isComplete()) {
      throw new UnsupportedOperationException("Service is already complete!");
    }

    Service removedService = foundWorkOrder.removeService(serviceID);

    if (removedService == null) {
      throw new UnsupportedOperationException(
          "Order current state doesn't support removal of service!");
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return removedService;
  }

  /**
   * Retrieves a list of all the <code>Service</code>s related to a given <code>WorkOrder</code>.
   *
   * @param workOrderID The ID of the targeted <code>WorkOrder</code>.
   * @return A list of <code>Service</code>s.
   * @throws WorkOrderNotFoundException If the <code>workOrderID</code> doesn't match any of the
   *     ones from the registered <code>WorkOrder</code>s.
   * @see Service
   * @see WorkOrder
   */
  public static List<Service> listServices(String workOrderID) throws WorkOrderNotFoundException {
    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(workOrderID);

    if (foundWorkOrder == null) {
      throw new WorkOrderNotFoundException("Order not found!");
    }

    return DAO.fromService().findByWorkOrder(workOrderID);
  }

  /**
   * Updates a <code>Service</code> by marking it as done, that is, setting its <code>isDone</code>
   * attribute to <code>true</code>.
   *
   * @param serviceID The ID of the targeted <code>Service</code>.
   * @return The updated <code>Service</code>.
   * @throws ServiceNotFoundException If the <code>serviceID</code> doesn't match any of the ones
   *     from the registered <code>Service</code>s.
   * @see Service
   */
  public static Service markAsDone(String serviceID) throws ServiceNotFoundException {
    Service foundService = DAO.fromService().findByID(serviceID);

    if (foundService == null) {
      throw new ServiceNotFoundException("Service not found!");
    }

    foundService.setComplete(true);

    DAO.fromService().updateInformation(foundService);

    return foundService;
  }

  /**
   * Attempts to open a <code>WorkOrder</code> for a given <code>Technician</code>, depending on the
   * current <code>State</code> of both of them.
   *
   * @param technicianID The ID of the <code>Technician</code>.
   * @return The updated <code>WorkOrder</code>.
   * @throws UserNotFoundException If the <code>technicianID</code> doesn't match any of the ones
   *     from the registered <code>Technician</code>s.
   * @throws WorkOrderNotFoundException If there's no <code>WorkOrder</code> available to be opened.
   * @throws UnsupportedOperationException If the current <code>Technician</code> already has a
   *     <code>WorkOrder</code> opened.
   * @see Technician
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   * @see WorkOrder
   * @see com.gersonfaneto.yams.models.orders.work.states.State
   */
  public static WorkOrder openWorkOrder(String technicianID)
      throws UserNotFoundException, WorkOrderNotFoundException, UnsupportedOperationException {
    Technician foundTechnician = (Technician) DAO.fromUsers().findByID(technicianID);

    if (DAO.fromUsers().findByID(technicianID) == null) {
      throw new UserNotFoundException("User not found");
    }

    WorkOrder foundWorkOrder =
        DAO.fromWorkOrders().findMany().stream()
            .filter(x -> x.getWorkOrderState() instanceof Created)
            .min(Comparator.comparing(WorkOrder::getCreatedAt))
            .stream()
            .findFirst()
            .orElse(null);

    if (foundWorkOrder == null) {
      throw new WorkOrderNotFoundException("No order available at the moment!");
    }

    if (!foundTechnician.openOrder(foundWorkOrder)) {
      throw new UnsupportedOperationException("You already have an order open!");
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return foundWorkOrder;
  }

  /**
   * Attempts to close the <code>WorkOrder</code> of a given <code>Technician</code>, depending on
   * the current <code>State</code> of both of them.
   *
   * @param technicianID The ID of the <code>Technician</code>.
   * @return The updated <code>WorkOrder</code>.
   * @throws UserNotFoundException If the <code>technicianID</code> doesn't match any of the ones
   *     from the registered <code>Technician</code>s.
   * @throws UnsupportedOperationException If the <code>Technician</code> doesn't have an opened
   *     <code>WorkOrder</code> or if there are any <code>Service</code>s on the <code>WorkOrder
   *     </code> still pending.
   * @see Technician
   * @see com.gersonfaneto.yams.models.entities.technician.states.State
   * @see WorkOrder
   * @see Service
   */
  public static WorkOrder closeWorkOrder(String technicianID)
      throws UserNotFoundException, UnsupportedOperationException {
    Technician foundTechnician = (Technician) DAO.fromUsers().findByID(technicianID);

    if (foundTechnician == null) {
      throw new UserNotFoundException("User not found!");
    }

    WorkOrder foundWorkOrder = foundTechnician.getTechnicianState().getWorkOrder();

    if (foundWorkOrder == null) {
      throw new UnsupportedOperationException("You don't have an order open!");
    }

    if (!foundTechnician.closeOrder()) {
      throw new UnsupportedOperationException("There are services still pending!");
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return foundWorkOrder;
  }

  /**
   * Attempts to cancel the <code>WorkOrder</code> of a given <code>Technician</code>, depending on
   * the current <code>State</code> of both of them.
   *
   * @param technicianID The ID of the <code>Technician</code>.
   * @return The updated <code>WorkOrder</code>.
   * @throws UserNotFoundException If the <code>technicianID</code> doesn't match any of the ones
   *     from the registered <code>Technician</code>s.
   * @throws UnsupportedOperationException If the <code>Technician</code> doesn't have an opened
   *     <code>WorkOrder</code> or if there are any <code>Service</code>s on the <code>WorkOrder
   *     </code> still pending.
   */
  public static WorkOrder cancelWorkOrder(String technicianID)
      throws UserNotFoundException, UnsupportedOperationException {
    Technician foundTechnician = (Technician) DAO.fromUsers().findByID(technicianID);

    if (foundTechnician == null) {
      throw new UserNotFoundException("User not found!");
    }

    WorkOrder foundWorkOrder = foundTechnician.getTechnicianState().getWorkOrder();

    if (foundWorkOrder == null) {
      throw new UnsupportedOperationException("You don't have an order open!");
    }

    if (!foundTechnician.cancelOrder()) {
      throw new UnsupportedOperationException("There are services still pending!");
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return foundWorkOrder;
  }
}
