package com.gersonfaneto.yams.factories;

import com.gersonfaneto.yams.factories.types.PaymentFactory;

public abstract class AbstractFactory {
  private static PaymentFactory paymentFactory;

  public static PaymentFactory fromPayments() {
    if (paymentFactory == null) {
      paymentFactory = new PaymentFactory();
    }

    return paymentFactory;
  }
}
