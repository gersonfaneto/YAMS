package com.gersonfaneto.yams.exceptions.users;

public class PermissionDeniedException extends Exception {

  public PermissionDeniedException(String errorMessage) {
    super(errorMessage);
  }
}
