package com.gersonfaneto.techinfo.dao;

import com.gersonfaneto.techinfo.dao.client.ClientDAO;
import com.gersonfaneto.techinfo.dao.client.ClientListCRUD;

public abstract class DAO {
    private static ClientDAO registeredClients;

    public static ClientDAO getClients() {
        if (registeredClients == null) {
            registeredClients = new ClientListCRUD();
        }
        return registeredClients;
    }

}
