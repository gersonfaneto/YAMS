package com.gersonfaneto.yams.dao.entities.client;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.Client;

public interface ClientCRUD extends CRUD<Client> {
    public Client findByName(String clientName);
}
