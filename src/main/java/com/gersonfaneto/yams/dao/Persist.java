package com.gersonfaneto.yams.dao;

/**
 * Defines a <code>interface</code> for the basic persistence operations.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public interface Persist {

  /**
   * Saves all the contents stored in the <code>HashMap</code> into a file using the
   * <code>ObjectIO</code>.
   *
   * @return <code>true</code> if the saving of the data was successful, or <code>false</code> if it
   * wasn't.
   * @see ObjectIO
   */
  boolean saveAll();

  /**
   * Loads all the contents of the save file into in to the <code>HashMap</code>.
   *
   * @return <code>true</code> if the <code>Invoice</code>s wore loaded from disk successfully, or
   * <code>false</code> if they weren't.
   */
  boolean loadAll();
}
