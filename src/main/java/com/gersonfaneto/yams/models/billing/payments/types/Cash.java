package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class Cash extends Payment {
  private static final String PAYMENT_METHOD = "Cash";
  public Cash(String invoiceID, double paidValue) {
    super(PAYMENT_METHOD, invoiceID, paidValue);
  }
}
