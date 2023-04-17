package com.gersonfaneto.yams.exceptions.users;

import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

/**
 * Thrown when a <code>User</code> tries to perform an operation which his <code>UserType</code> he
 * doesn't have access to.
 *
 * @see UserType
 * @see User
 */
public class PermissionDeniedException extends Exception {

  /**
   * Constructs a new <code>PermissionDeniedException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public PermissionDeniedException(String errorMessage) {
    super(errorMessage);
  }
}
