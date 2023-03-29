package com.gersonfaneto.techinfo.dao.client;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.entities.Client;

public interface ClientDAO extends CRUD<Client> {
    Client findByName(String clientName);
}
