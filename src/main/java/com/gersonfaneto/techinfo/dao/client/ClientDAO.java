package com.gersonfaneto.techinfo.dao.client;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.Client;

public interface ClientDAO extends CRUD<Client> {
    public Client findByName(String clientName);
}
