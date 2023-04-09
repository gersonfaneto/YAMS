package com.gersonfaneto.yams.exceptions;

public class InvalidPasswordException extends Exception {

  public InvalidPasswordException(String errorMessage) {
    super(errorMessage);
  }
}
