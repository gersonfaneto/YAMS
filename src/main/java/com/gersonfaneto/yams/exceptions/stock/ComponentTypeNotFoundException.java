package com.gersonfaneto.yams.exceptions.stock;

import com.gersonfaneto.yams.dao.stock.ComponentCRUD;
import com.gersonfaneto.yams.dao.stock.ComponentMemoryDAO;
import com.gersonfaneto.yams.models.stock.Component;

/**
 * Thrown when a <code>Component</code> isn't found by the DAO.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see ComponentMemoryDAO
 * @see ComponentCRUD
 * @see Component
 */
public class ComponentTypeNotFoundException extends Exception {

  /**
   * Constructs a new <code>ComponentTypeNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public ComponentTypeNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
