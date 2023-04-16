package com.gersonfaneto.yams.exceptions.billing;

public class PaymentMethodNotFoundException extends Exception {

  public PaymentMethodNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
