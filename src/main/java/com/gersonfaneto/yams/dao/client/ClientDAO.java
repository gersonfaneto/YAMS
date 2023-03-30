package com.gersonfaneto.yams.dao.client;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.Client;

public interface ClientDAO extends CRUD<Client> {
    Client findByName(String clientName);
}
