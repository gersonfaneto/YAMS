package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.Client;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.security.InvalidParameterException;
import java.util.List;

public abstract class ClientController {

  public static Client registerClient(String clientName, String homeAddress, String phoneNumber) {
    return DAO.fromClients().createOne(new Client(clientName, homeAddress, phoneNumber));
  }

  public static Client unregisterClient(String clientName) {
    if (DAO.fromClients().findByName(clientName) == null) {
      throw new InvalidParameterException("Client not found!");
    }

    Client foundClient = DAO.fromClients().findByName(clientName);
    DAO.fromClients().deleteByID(foundClient.getClientID());

    return foundClient;
  }

  public static Client findClient(String clientName) {
    if (DAO.fromClients().findByName(clientName) == null) {
      throw new InvalidParameterException("Client not found!");
    }

    return DAO.fromClients().findByName(clientName);
  }

  public static Client updateInfo(String clientName, String homeAddress, String phoneNumber) {
    if (DAO.fromClients().findByName(clientName) == null) {
      throw new InvalidParameterException("Client not found!");
    }

    Client foundClient = DAO.fromClients().findByName(clientName);

    foundClient.setClientName(clientName);
    foundClient.setHomeAddress(homeAddress);
    foundClient.setPhoneNumber(phoneNumber);

    DAO.fromClients().updateInformation(foundClient);

    return foundClient;
  }

  public static List<WorkOrder> retrieveWorkOrders(String clientName) {
    if (DAO.fromClients().findByName(clientName) == null) {
      throw new InvalidParameterException("Client not found!");
    }

    Client foundClient = DAO.fromClients().findByName(clientName);

    return DAO.fromWorkOrders().findByClient(foundClient.getClientID());
  }
}
