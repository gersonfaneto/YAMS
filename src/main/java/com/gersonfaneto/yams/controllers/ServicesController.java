package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.ClientNotFoundException;
import com.gersonfaneto.yams.exceptions.ServiceNotFound;
import com.gersonfaneto.yams.exceptions.ServiceTypeNotFound;
import com.gersonfaneto.yams.exceptions.UnsupportedOperationException;
import com.gersonfaneto.yams.exceptions.UserNotFoundException;
import com.gersonfaneto.yams.exceptions.WorkOrderNotFound;
import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import java.util.Comparator;
import java.util.List;

public abstract class ServicesController {

  public static WorkOrder createWorkOrder(String clientName, List<Service> chosenServices)
      throws ClientNotFoundException {
    if (DAO.fromClients().findByName(clientName) == null) {
      throw new ClientNotFoundException("Client '" + clientName + "' not found!");
    }

    Client foundClient = DAO.fromClients().findByName(clientName);
    WorkOrder workOrder = new WorkOrder(foundClient.getClientID());

    for (Service currentService : chosenServices) {
      currentService.setWorkOrderID(workOrder.getWorkOrderID());
      DAO.fromService().updateInformation(currentService);
    }

    return DAO.fromWorkOrders().createOne(workOrder);
  }

  public static Service createService(
      String serviceType,
      String serviceDescription,
      List<Component> usedComponents
  ) throws ServiceTypeNotFound {
    if (ServiceType.findByName(serviceType) == null) {
      throw new ServiceTypeNotFound("Service type '" + serviceType + "' not found!");
    }

    Service newService = new Service(
        ServiceType.findByName(serviceType),
        serviceDescription,
        usedComponents
    );

    return DAO.fromService().createOne(newService);
  }

  public static void removeService(String workOrderID, String serviceID)
      throws ServiceNotFound, WorkOrderNotFound, UnsupportedOperationException {
    if (DAO.fromWorkOrders().findByID(workOrderID) == null) {
      throw new WorkOrderNotFound("Work Order with ID '" + workOrderID + "' not found!");
    }

    if (DAO.fromService().findByID(serviceID) == null) {
      throw new ServiceNotFound("Service with ID '" + serviceID + "' not found!");
    }

    WorkOrder workOrder = DAO.fromWorkOrders().findByID(workOrderID);

    if (DAO.fromService().findByID(serviceID).isComplete()) {
      throw new UnsupportedOperationException("Service is already complete!");
    }

    Service removedService = workOrder.removeService(serviceID);

    if (removedService == null) {
      throw new UnsupportedOperationException(
          "Order current state doesn't support removal of service!"
      );
    }
  }

  public static List<Service> listServices(String workOrderID) throws WorkOrderNotFound {
    if (DAO.fromWorkOrders().findByID(workOrderID) == null) {
      throw new WorkOrderNotFound("Work Order with ID '" + workOrderID + "' not found!");
    }

    return DAO.fromService().findByWorkOrder(workOrderID);
  }

  public static void markAsDone(String serviceID) throws ServiceNotFound {
    if (DAO.fromService().findByID(serviceID) == null) {
      throw new ServiceNotFound("Service with ID '" + serviceID + "' not found!");
    }

    Service foundService = DAO.fromService().findByID(serviceID);

    foundService.setComplete(true);

    DAO.fromService().updateInformation(foundService);
  }

  public static WorkOrder openWorkOrder(String technicianID)
      throws UserNotFoundException, WorkOrderNotFound, UnsupportedOperationException {
    if (DAO.fromUsers().findByID(technicianID) == null) {
      throw new UserNotFoundException("Technician with ID' " + technicianID + "' not found!");
    }

    Technician foundTechnician = (Technician) DAO.fromUsers().findByID(technicianID);

    WorkOrder foundWorkOrder = DAO.fromWorkOrders()
        .findMany()
        .stream()
        .filter(x -> x.getClosedAt() == null)
        .min(Comparator.comparing(WorkOrder::getCreatedAt))
        .stream()
        .findFirst()
        .orElse(null);

    if (foundWorkOrder == null) {
      throw new WorkOrderNotFound("No Work Order avaliable to open!");
    }

    if (!foundTechnician.openOrder(foundWorkOrder)) {
      throw new UnsupportedOperationException(
          "Technician with ID '" + technicianID + "' already has a Work Order!"
      );
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return foundWorkOrder;
  }

  public static WorkOrder closeWorkOrder(String technicianID)
      throws WorkOrderNotFound, UserNotFoundException, UnsupportedOperationException {
    if (DAO.fromUsers().findByID(technicianID) == null) {
      throw new UserNotFoundException("Technician with ID '" + technicianID + "' not found!");
    }

    Technician foundTechnician = (Technician) DAO.fromUsers().findByID(technicianID);
    WorkOrder foundWorkOrder = foundTechnician.getTechnicianState().getWorkOrder();

    if (!foundTechnician.closeOrder()) {
      throw new UnsupportedOperationException(
          "Technician with ID '" + technicianID + "' doesn't have a Work Order"
      );
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return foundWorkOrder;
  }

  public static WorkOrder cancelWorkOrder(String technicianID)
      throws UserNotFoundException, UnsupportedOperationException {
    if (DAO.fromUsers().findByID(technicianID) == null) {
      throw new UserNotFoundException("Technician with ID '" + technicianID + "' not found!");
    }

    Technician foundTechnician = (Technician) DAO.fromUsers().findByID(technicianID);
    WorkOrder foundWorkOrder = foundTechnician.getTechnicianState().getWorkOrder();

    if (!foundTechnician.cancelOrder()) {
      throw new UnsupportedOperationException(
          "Technician with ID '" + technicianID + "' doesn't have a Work Order");
    }

    DAO.fromWorkOrders().updateInformation(foundWorkOrder);

    return foundWorkOrder;
  }
}
