package com.gersonfaneto.yams.exceptions.users;

import com.gersonfaneto.yams.models.entities.user.User;

/**
 * Thrown when is tried to create a new <code>User</code> with the same email of one already
 * registered.
 *
 * @see User
 */
public class UserAlreadyRegisteredException extends Exception {

  /**
   * Constructs a new <code>UserAlreadyRegisteredException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public UserAlreadyRegisteredException(String errorMessage) {
    super(errorMessage);
  }
}
