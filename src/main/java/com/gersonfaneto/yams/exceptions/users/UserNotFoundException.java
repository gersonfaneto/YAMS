package com.gersonfaneto.yams.exceptions.users;

public class UserNotFoundException extends Exception {

  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
