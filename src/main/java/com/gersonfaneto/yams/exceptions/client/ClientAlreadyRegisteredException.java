package com.gersonfaneto.yams.exceptions.client;

public class ClientAlreadyRegisteredException extends Exception {

  public ClientAlreadyRegisteredException(String errorMessage) {
    super(errorMessage);
  }
}
