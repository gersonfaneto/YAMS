package com.gersonfaneto.yams.dao.entities.user;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.entities.user.User;

/**
 * Extends the <code>CRUD</code> interface by adding operations specific to the <code>User</code>
 * models.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see User
 */
public interface UserCRUD extends CRUD<User>, Persist {

  /**
   * Searches for a <code>User</code> by its email.
   *
   * @param userEmail The targeted <code>User</code> email.
   * @return The <code>User</code> itself if found, or <code>null</code> if not.
   */
  public User findByEmail(String userEmail);
}
