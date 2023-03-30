package com.gersonfaneto.yams.dao.client;

import com.gersonfaneto.yams.models.entities.Client;

import java.util.*;

public class ClientListCRUD implements ClientDAO {
    private final Map<String, Client> registeredClients;

    public ClientListCRUD() {
        this.registeredClients = new HashMap<>();
    }

    @Override
    public Client createOne(Client newClient) {
        String newID = UUID.randomUUID().toString();

        newClient.setClientID(newID);

        registeredClients.put(newID, newClient);

        return newClient;
    }

    @Override
    public Client findByID(String targetID) {
        return registeredClients.get(targetID);
    }

    @Override
    public List<Client> findMany() {
        List<Client> foundClients = new LinkedList<>();

        for (Client currentClient : registeredClients.values()) {
            foundClients.add(currentClient);
        }

        return foundClients;
    }

    @Override
    public boolean updateInformation(Client targetClient) {
        String clientID = targetClient.getClientID();

        if (registeredClients.containsKey(clientID)) {
            registeredClients.put(clientID, targetClient);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredClients.containsKey(targetID)) {
            registeredClients.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredClients.isEmpty()) {
            registeredClients.clear();
            return true;
        }

        return false;
    }

    @Override
    public Client findByName(String clientName) {
        for (Client currentClient : registeredClients.values()) {
            if (currentClient.getClientName().equals(clientName)) {
                return currentClient;
            }
        }

        return null;
    }
}
