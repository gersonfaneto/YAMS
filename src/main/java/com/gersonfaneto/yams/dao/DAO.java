package com.gersonfaneto.yams.dao;

import com.gersonfaneto.yams.dao.entities.client.ClientCRUD;
import com.gersonfaneto.yams.dao.entities.client.ClientMemoryDAO;
import com.gersonfaneto.yams.dao.entities.receptionist.ReceptionistCRUD;
import com.gersonfaneto.yams.dao.entities.receptionist.ReceptionistMemoryDAO;

public abstract class DAO {
    private static ClientCRUD clientCRUD;
    private static ReceptionistCRUD receptionistCRUD;

    public static ClientCRUD fromClients() {
        if (clientCRUD == null) {
            clientCRUD = new ClientMemoryDAO();
        }

        return clientCRUD;
    }

    public static ReceptionistCRUD fromReceptionists() {
        if (receptionistCRUD == null) {
            receptionistCRUD = new ReceptionistMemoryDAO();
        }

        return receptionistCRUD;
    }
}
