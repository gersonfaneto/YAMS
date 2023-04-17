package com.gersonfaneto.yams.exceptions.users;

import com.gersonfaneto.yams.dao.entities.user.UserCRUD;
import com.gersonfaneto.yams.dao.entities.user.UserMemoryDAO;
import com.gersonfaneto.yams.models.entities.user.User;

/**
 * Thrown when a <code>User</code> isn't found by the DAO.
 *
 * @see UserMemoryDAO
 * @see UserCRUD
 * @see User
 */
public class UserNotFoundException extends Exception {

  /**
   * Constructs a new <code>UserNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
