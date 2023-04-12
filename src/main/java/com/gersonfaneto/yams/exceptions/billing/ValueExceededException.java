package com.gersonfaneto.yams.exceptions.billing;

public class ValueExceededException extends Exception {

  public ValueExceededException(String errorMessage) {
    super(errorMessage);
  }
}
