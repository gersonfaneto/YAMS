package com.gersonfaneto.yams.dao;

import com.gersonfaneto.yams.dao.client.ClientCRUD;
import com.gersonfaneto.yams.dao.client.ClientMemoryDAO;

public abstract class DAO {
    private static ClientCRUD clientCRUD;

    public static ClientCRUD fromClients() {
        if (clientCRUD == null) {
            clientCRUD = new ClientMemoryDAO();
        }

        return clientCRUD;
    }
}
