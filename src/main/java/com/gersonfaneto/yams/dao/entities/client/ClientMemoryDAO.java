package com.gersonfaneto.yams.dao.entities.client;

import com.gersonfaneto.yams.models.entities.Client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ClientMemoryDAO implements ClientCRUD {
    private final Map<String, Client> storedClients;

    public ClientMemoryDAO() {
        this.storedClients = new HashMap<>();
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
        return storedClients.values().stream().toList();
    }

    @Override
    public boolean updateInformation(Client updatedClient) {
        if (storedClients.containsValue(updatedClient)) {
            storedClients.put(updatedClient.getClientID(), updatedClient);
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
    public Client findByName(String clientName) {
        for (Client currentClient : storedClients.values()) {
            if (currentClient.getClientName().equals(clientName)) {
                return currentClient;
            }
        }

        return null;
    }
}
