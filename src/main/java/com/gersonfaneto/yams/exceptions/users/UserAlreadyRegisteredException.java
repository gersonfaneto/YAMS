package com.gersonfaneto.yams.exceptions.users;

public class UserAlreadyRegisteredException extends Exception {

  public UserAlreadyRegisteredException(String errorMessage) {
    super(errorMessage);
  }
}
