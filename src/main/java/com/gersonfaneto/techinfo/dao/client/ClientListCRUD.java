package com.gersonfaneto.techinfo.dao.client;

import com.gersonfaneto.techinfo.models.Client;

import java.util.LinkedList;
import java.util.List;

public class ClientListCRUD implements ClientDAO {
    private List<Client> clientList;
    private int referenceID;

    public ClientListCRUD() {
        this.clientList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public Client createOne(Client targetObject) {
        targetObject.setClientID(++referenceID);
        clientList.add(targetObject);
        return targetObject;
    }

    @Override
    public Client findByID(int targetID) {
        for (Client currentClient : clientList) {
            if (currentClient.getClientID() == targetID) {
                return currentClient;
            }
        }
        return null;
    }

    @Override
    public List<Client> findMany() {
        return clientList;
    }

    @Override
    public boolean updateInformation(Client targetObject) {
        for (Client currentClient : clientList) {
            if (currentClient.getClientID() == targetObject.getClientID()) {
                int targetIndex = clientList.indexOf(currentClient);
                clientList.set(targetIndex, targetObject);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (Client currentClient : clientList) {
            if (currentClient.getClientID() == targetID) {
                clientList.remove(currentClient);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!clientList.isEmpty()) {
            clientList.clear();
            return true;
        };
        return false;
    }

    @Override
    public Client findByName(String clientName) {
        for (Client currentClient : clientList) {
            if (currentClient.getClientName().equals(clientName)) {
                return currentClient;
            }
        };
        return null;
    }
}
