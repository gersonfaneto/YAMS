package com.gersonfaneto.yams.dao.entities.client;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.client.Client;
import java.util.List;

public interface ClientCRUD extends CRUD<Client> {

  List<Client> findByName(String clientName);
}
