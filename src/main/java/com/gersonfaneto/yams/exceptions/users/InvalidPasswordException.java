package com.gersonfaneto.yams.exceptions.users;

import com.gersonfaneto.yams.models.entities.user.User;

/**
 * Thrown when a <code>User</code> tries to login using a invalid password.
 *
 * @see User
 */
public class InvalidPasswordException extends Exception {

  /**
   * Constructs a new <code>InvalidPasswordException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public InvalidPasswordException(String errorMessage) {
    super(errorMessage);
  }
}
