package com.gersonfaneto.yams.exceptions;

public class PermissionDeniedException extends Exception {

  public PermissionDeniedException(String errorMessage) {
    super(errorMessage);
  }
}
