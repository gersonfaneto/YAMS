package com.gersonfaneto.yams.exceptions;

public class ClientAlreadyRegisteredException extends Exception {

  public ClientAlreadyRegisteredException(String errorMessage) {
    super(errorMessage);
  }
}
