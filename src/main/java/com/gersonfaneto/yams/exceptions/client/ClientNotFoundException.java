package com.gersonfaneto.yams.exceptions.client;

import com.gersonfaneto.yams.dao.entities.client.ClientCRUD;
import com.gersonfaneto.yams.dao.entities.client.ClientMemoryDAO;
import com.gersonfaneto.yams.models.entities.client.Client;

/**
 * Thrown when a <code>Client</code> isn't found by the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see ClientMemoryDAO
 * @see ClientCRUD
 * @see Client
 */
public class ClientNotFoundException extends Exception {

  /**
   * Constructs a new <code>ClientNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public ClientNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
