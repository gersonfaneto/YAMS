package com.gersonfaneto.yams.exceptions;

public class UserNotFoundException extends Exception {

  public UserNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
