package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.entities.client.ClientCRUD;
import com.gersonfaneto.yams.dao.entities.client.ClientMemoryDAO;
import com.gersonfaneto.yams.exceptions.client.ClientNotFoundException;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.util.List;

/**
 * Controller containing all the operations related with the <code>Client</code> model on the
 * System, such as creating, updating and retrieving information about it. Most of the operations
 * relly on the CRUD operations accessed through the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @see ClientMemoryDAO
 * @see ClientCRUD
 * @see Client
 */
public abstract class ClientController {

  /**
   * Registers a new <code>Client</code> in the System.
   *
   * @param clientName  The name of the <code>Client</code>.
   * @param homeAddress The home address of the <code>Client</code>.
   * @param phoneNumber The phone number of the <code>Client</code>
   * @return The registered <code>Client</code>.
   */
  public static Client registerClient(
      String clientName,
      String homeAddress,
      String phoneNumber
  ) {
    return DAO.fromClients().createOne(new Client(clientName, homeAddress, phoneNumber));
  }

  /**
   * Unregisters a <code>Client</code> of the System.
   *
   * @param clientID The ID of the targeted <code>Client</code>.
   * @return The removed <code>Client</code>.
   * @throws ClientNotFoundException If the <code>clientID</code> didn't match any of the ones from
   *                                 the registered <code>Client</code>s.
   */
  public static Client unregisterClient(
      String clientID
  ) throws ClientNotFoundException {
    Client foundClient = DAO.fromClients().findByID(clientID);

    if (foundClient == null) {
      throw new ClientNotFoundException("Client not found!");
    }

    DAO.fromClients().deleteByID(clientID);

    return foundClient;
  }

  /**
   * Searches for a <code>Client</code> by its ID.
   *
   * @param clientID The ID of the targeted <code>Client</code>.
   * @return The found <code>Client</code>.
   * @throws ClientNotFoundException If the <code>clientID</code> didn't match any of the ones from
   *                                 the registered <code>Client</code>s.
   */
  public static Client findClient(
      String clientID
  ) throws ClientNotFoundException {
    Client foundClient = DAO.fromClients().findByID(clientID);

    if (foundClient == null) {
      throw new ClientNotFoundException("Client not found!");
    }

    return foundClient;
  }

  /**
   * Searches for all the <code>Client</code>s with a given name.
   *
   * @param clientName A name to search for.
   * @return A list of the found <code>Client</code>s.
   */
  public static List<Client> findClients(String clientName) {
    return DAO.fromClients().findByName(clientName);
  }

  /**
   * Update the information of a given <code>Client</code>.
   *
   * @param clientID    The ID of the targeted <code>Client</code>.
   * @param clientName  The updated name of the <code>Client</code>.
   * @param homeAddress The updated home address of the <code>Client</code>.
   * @param phoneNumber The updated phone number of the <code>Client</code>.
   * @return The updated <code>Client</code>.
   * @throws ClientNotFoundException If the <code>clientID</code> didn't match any of the ones from
   *                                 the registered <code>Client</code>s.
   */
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

  /**
   * Searches for all the <code>WorkOrder</code>s related to a given <code>Client</code>.
   *
   * @param clientID The ID of the targeted <code>Client</code>.
   * @return The list of the found <code>WorkOrder</code>s.
   * @throws ClientNotFoundException If the <code>clientID</code> didn't match any of the ones from
   *                                 the registered <code>Client</code>s.
   */
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
