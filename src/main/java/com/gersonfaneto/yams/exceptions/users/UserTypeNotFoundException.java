package com.gersonfaneto.yams.exceptions.users;

import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

/**
 * Thrown when a <code>UserType</code> doesn't match any of the ones declared under the <code>
 * UserType</code> enumeration.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see UserType
 * @see User
 */
public class UserTypeNotFoundException extends Exception {

  /**
   * Constructs a new <code>UserTypeNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public UserTypeNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
