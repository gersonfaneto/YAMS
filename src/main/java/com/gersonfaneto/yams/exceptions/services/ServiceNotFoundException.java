package com.gersonfaneto.yams.exceptions.services;

public class ServiceNotFoundException extends Exception {

  public ServiceNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
