package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class DebitCard extends Payment {

  private static final String PAYMENT_METHOD = "Debit Card";

  public DebitCard(String invoiceID, double paidValue) {
    super(PAYMENT_METHOD, invoiceID, paidValue);
  }

  public DebitCard() {
  }

  @Override
  public DebitCard clone(String invoiceID, double paidValue) {
    return new DebitCard(invoiceID, paidValue);
  }
}
