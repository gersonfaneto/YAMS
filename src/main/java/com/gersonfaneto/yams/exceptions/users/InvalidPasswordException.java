package com.gersonfaneto.yams.exceptions.users;

public class InvalidPasswordException extends Exception {

  public InvalidPasswordException(String errorMessage) {
    super(errorMessage);
  }
}
