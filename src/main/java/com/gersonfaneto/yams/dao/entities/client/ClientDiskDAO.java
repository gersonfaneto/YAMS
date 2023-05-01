package com.gersonfaneto.yams.dao.entities.client;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.ObjectIO;
import com.gersonfaneto.yams.dao.billing.paymet.PaymentDiskDAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Implementations for the <code>ClientCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> as a cache to store all the <code>Client</code>s during the execution of the
 * program and loads or unloads the contents of it into a file using an <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see ClientCRUD
 * @see ObjectIO
 */
public class ClientDiskDAO implements ClientCRUD {

  private final Map<String, Client> storedClients;
  private final ObjectIO<Client> clientObjectIO;

  /**
   * Constructs a new <code>{@link ClientDiskDAO}</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public ClientDiskDAO(String savePath) {
    this.storedClients = new HashMap<>();
    this.clientObjectIO = new ObjectIO<>(savePath);
  }

  /**
   * Saves all the contents stored in the <code>HashMap</code> into a file using the
   * <code>ObjectIO</code>.
   *
   * @return <code>true</code> if the saving of the data was successful, or <code>false</code> if it
   * wasn't.
   * @see ObjectIO
   */
  public boolean saveAll() {
    List<Client> toSave = storedClients.values()
        .stream()
        .toList();

    return clientObjectIO.saveObjects(toSave);
  }

  /**
   * Loads all the contents of the save file into in to the <code>HashMap</code>.
   *
   * @return <code>true</code> if the <code>Invoice</code>s wore loaded from disk successfully, or
   * <code>false</code> if they weren't.
   */
  public boolean loadAll() {
    List<Client> loadedClients = clientObjectIO.loadObjects();

    if (loadedClients == null) {
      return false;
    }

    for (Client currentClient : loadedClients) {
      storedClients.put(currentClient.getClientID(), currentClient);
    }

    return true;
  }

  @Override
  public Client createOne(Client newClient) {
    String newID = UUID.randomUUID().toString();

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
