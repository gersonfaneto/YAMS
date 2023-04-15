package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.client.ClientNotFoundException;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.util.List;

public abstract class ClientController {

  public static Client registerClient(
      String clientName,
      String homeAddress,
      String phoneNumber
  ) {
    return DAO.fromClients().createOne(new Client(clientName, homeAddress, phoneNumber));
  }

  public static Client unregisterClient(String clientID) throws ClientNotFoundException {
    Client foundClient = DAO.fromClients().findByID(clientID);

    if (foundClient == null) {
      throw new ClientNotFoundException("Client not found!");
    }

    DAO.fromClients().deleteByID(clientID);

    return foundClient;
  }

  public static Client findClient(String clientID) throws ClientNotFoundException {
    Client foundClient = DAO.fromClients().findByID(clientID);

    if (foundClient == null) {
      throw new ClientNotFoundException("Client not found!");
    }

    return foundClient;
  }

  public static List<Client> findClients(String clientName) {
    return DAO.fromClients().findByName(clientName);
  }

  public static Client updateInfo(
      String clientID,
      String clientName,
      String homeAddress,
      String phoneNumber
  ) throws ClientNotFoundException {
    Client foundClient = DAO.fromClients().findByID(clientID);

    if (foundClient == null) {
      throw new ClientNotFoundException("Client not found!");
    }

    foundClient.setClientName(clientName);
    foundClient.setHomeAddress(homeAddress);
    foundClient.setPhoneNumber(phoneNumber);

    DAO.fromClients().updateInformation(foundClient);

    return foundClient;
  }

  public static List<WorkOrder> retrieveWorkOrders(
      String clientID
  ) throws ClientNotFoundException {
    Client foundClient = DAO.fromClients().findByID(clientID);

    if (foundClient == null) {
      throw new ClientNotFoundException("Client not found!");
    }

    return DAO.fromWorkOrders().findByClient(foundClient.getClientID());
  }
}
