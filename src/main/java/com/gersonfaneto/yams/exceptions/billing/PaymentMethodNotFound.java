package com.gersonfaneto.yams.exceptions.billing;

public class PaymentMethodNotFound extends Exception {

  public PaymentMethodNotFound(String errorMessage) {
    super(errorMessage);
  }
}
