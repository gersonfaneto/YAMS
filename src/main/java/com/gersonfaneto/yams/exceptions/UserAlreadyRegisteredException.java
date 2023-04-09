package com.gersonfaneto.yams.exceptions;

public class UserAlreadyRegisteredException extends Exception {

  public UserAlreadyRegisteredException(String errorMessage) {
    super(errorMessage);
  }
}
