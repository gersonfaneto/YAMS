package com.gersonfaneto.yams.dao.stock;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;

/**
 * Extends the <code>CRUD</code> interface by adding operations specific to the
 * <code>Component</code> models.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see Component
 */
public interface ComponentCRUD extends CRUD<Component> {

  /**
   * Searches for all the <code>Component</code>s of a given type.
   *
   * @param componentType The targeted <code>Component</code> type.
   * @return The list of all the found <code>Component</code>s.
   */
  List<Component> findByType(ComponentType componentType);

  /**
   * Searches for a equivalent to a given <code>Component</code> by using the <code>equals</code>
   * method of <code>Component</code>.
   *
   * @param otherObject The <code>Object</code> to be compared.
   * @return The equivalent <code>Object</code> to the one provided.
   * @see Component
   */
  Component findEquals(Object otherObject);
}
