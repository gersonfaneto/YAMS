package com.gersonfaneto.yams.dao;

import java.util.List;

/**
 * Defines a generic <code>interface</code> for the basic <code>CRUD</code> operations.
 *
 * @param <T> The type to be manipulated in the <code>CRUD</code> operations.
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public interface CRUD<T> {

  /**
   * Inserts a given <code>Object</code> in the Database, by defining its ID.
   *
   * @param newObject The <code>Object</code> to be inserted.
   * @return The updated <code>Object</code>.
   */
  T createOne(T newObject);

  /**
   * Searches for a <code>Object</code> given its ID.
   *
   * @param targetID The ID of the targeted <code>Object</code>.
   * @return The found <code>Object</code> or <code>null</code> if the <code>targetID</code> didn't
   * match any of the registered <code>Object</code>s.
   */
  T findByID(String targetID);

  /**
   * Retrieves all stored <code>Object</code>s.
   *
   * @return A list of all the found <code>Object</code>s.
   */
  List<T> findMany();

  /**
   * Updates a stored <code>Object</code> after its information was changed.
   *
   * @param updatedObject The updated <code>Object</code>.
   * @return <code>true</code> if the <code>Object</code> was found and updated, or <code>false
   * </code>, if it wasn't.
   */
  boolean updateInformation(T updatedObject);

  /**
   * Deletes a stored <code>Object</code> given its ID.
   *
   * @param targetID The ID of the targeted <code>Object</code>.
   * @return <code>true</code> if the <code>Object</code> was found and removed, or <code>false
   * </code>, if it wasn't.
   */
  boolean deleteByID(String targetID);

  /**
   * <strong>Deletes all stored <code>Object</code>s!<strong/>
   *
   * @return <code>true</code> if there wore any stored <code>Object</code>s and they were deleted,
   * of <code>false</code> if there weren't any.
   */
  boolean deleteMany();
}
