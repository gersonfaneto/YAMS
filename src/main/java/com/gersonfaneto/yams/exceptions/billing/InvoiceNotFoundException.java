package com.gersonfaneto.yams.exceptions.billing;

public class InvoiceNotFoundException extends Exception {

  public InvoiceNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
