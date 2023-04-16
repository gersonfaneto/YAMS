package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.client.ClientNotFoundException;
import com.gersonfaneto.yams.exceptions.services.ServiceNotFoundException;
import com.gersonfaneto.yams.exceptions.services.ServiceTypeNotFoundException;
import com.gersonfaneto.yams.exceptions.orders.UnsupportedOperationException;
import com.gersonfaneto.yams.exceptions.users.UserNotFoundException;
import com.gersonfaneto.yams.exceptions.orders.WorkOrderNotFoundException;
import com.gersonfaneto.yams.models.orders.work.states.Created;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import java.util.Comparator;
import java.util.List;

public abstract class ServicesController {

  public static WorkOrder createWorkOrder(
      String clientID,
      List<Service> chosenServices
  ) throws ClientNotFoundException {
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

  public static List<WorkOrder> listWorkOrders() {
    return DAO.fromWorkOrders()
        .findMany()
        .stream()
        .filter(x -> x.getWorkOrderState() instanceof Created)
        .min(Comparator.comparing(WorkOrder::getCreatedAt))
        .stream()
        .toList();
  }

  public static Service createService(
      String serviceType,
      String serviceDescription,
      List<Component> usedComponents
  ) throws ServiceTypeNotFoundException {
    if (ServiceType.findByName(serviceType) == null) {
      throw new ServiceTypeNotFoundException("Service type not found!");
    }

    Service newService = new Service(
        ServiceType.findByName(serviceType),
        serviceDescription,
        usedComponents
    );

    return DAO.fromService().createOne(newService);
  }

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
          "Order current state doesn't support removal of service!"
      );
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return removedService;
  }

  public static List<Service> listServices(String workOrderID) throws WorkOrderNotFoundException {
    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(workOrderID);

    if (foundWorkOrder == null) {
      throw new WorkOrderNotFoundException("Order not found!");
    }

    return DAO.fromService().findByWorkOrder(workOrderID);
  }

  public static Service markAsDone(String serviceID) throws ServiceNotFoundException {
    Service foundService = DAO.fromService().findByID(serviceID);

    if (foundService == null) {
      throw new ServiceNotFoundException("Service not found!");
    }

    foundService.setComplete(true);

    DAO.fromService().updateInformation(foundService);

    return foundService;
  }

  public static WorkOrder openWorkOrder(
      String technicianID
  ) throws UserNotFoundException, WorkOrderNotFoundException, UnsupportedOperationException {
    Technician foundTechnician = (Technician) DAO.fromUsers().findByID(technicianID);

    if (DAO.fromUsers().findByID(technicianID) == null) {
      throw new UserNotFoundException("User not found");
    }

    WorkOrder foundWorkOrder = DAO.fromWorkOrders()
        .findMany()
        .stream()
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

  public static WorkOrder closeWorkOrder(
      String technicianID
  ) throws UserNotFoundException, UnsupportedOperationException {
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

  public static WorkOrder cancelWorkOrder(
      String technicianID
  ) throws UserNotFoundException, UnsupportedOperationException {
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
