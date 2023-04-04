package com.gersonfaneto.yams.dao.client;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.Client;

import java.util.List;

public interface ClientCRUD extends CRUD<Client> {
    public Client findByName(String clientName);
}
