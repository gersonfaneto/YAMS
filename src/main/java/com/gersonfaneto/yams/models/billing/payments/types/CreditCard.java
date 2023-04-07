package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class CreditCard extends Payment {
  private static final String PAYMENT_METHOD = "Credit Card";

  public CreditCard(String invoiceID, double paidValue) {
    super(PAYMENT_METHOD, invoiceID, paidValue);
  }
}
