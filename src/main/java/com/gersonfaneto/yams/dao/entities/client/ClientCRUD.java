package com.gersonfaneto.yams.dao.entities.client;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.entities.client.Client;
import java.util.List;

/**
 * Extends the <code>CRUD</code> interface by adding the operations specific to the <code>Client
 * </code> models.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see Client
 */
public interface ClientCRUD extends CRUD<Client>, Persist {

  /**
   * Searches for all the <code>Client</code>s with a given name.
   *
   * @param clientName The targeted name.
   * @return The list of all the found <code>Client</code>s.
   */
  List<Client> findByName(String clientName);
}
