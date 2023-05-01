package com.gersonfaneto.yams.dao.entities.client;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>ClientCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> to store all the <code>Client</code>s.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see ClientCRUD
 */
public class ClientMemoryDAO implements ClientCRUD {

  private final Map<String, Client> storedClients;

  /**
   * Constructs a new <code>{@link ClientMemoryDAO}</code>
   */
  public ClientMemoryDAO() {
    this.storedClients = new HashMap<>();
  }

  @Override
  public Client createOne(Client newClient) {
    String newID = Generators.randomID();

    newClient.setClientID(newID);

    storedClients.put(newID, newClient);

    return newClient;
  }

  @Override
  public Client findByID(String targetID) {
    return storedClients.get(targetID);
  }

  @Override
  public List<Client> findMany() {
    return storedClients.values()
        .stream()
        .toList();
  }

  @Override
  public boolean updateInformation(Client updatedClient) {
    String clientID = updatedClient.getClientID();

    if (storedClients.containsKey(clientID)) {
      storedClients.put(clientID, updatedClient);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedClients.containsKey(targetID)) {
      storedClients.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedClients.isEmpty()) {
      storedClients.clear();
      return true;
    }

    return false;
  }

  @Override
  public List<Client> findByName(String clientName) {
    return storedClients.values()
        .stream()
        .filter(x -> x.getClientName().equals(clientName))
        .toList();
  }
}
