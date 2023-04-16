package com.gersonfaneto.yams.exceptions.orders;

public class WorkOrderNotFoundException extends Exception {

  public WorkOrderNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
