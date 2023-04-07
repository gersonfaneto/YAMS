package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class CreditCard extends Payment {
  private static final String PAYMENT_METHOD = "Credit Card";

  public CreditCard(String invoiceID, double paidValue) {
    super(PAYMENT_METHOD, invoiceID, paidValue);
  }

  public CreditCard() {
    super();
  }

  @Override
  public CreditCard clone(String invoiceID, double paidValue) {
    return new CreditCard(invoiceID, paidValue);
  }
}
