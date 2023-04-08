package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class Transference extends Payment {
  private static final String PAYMENT_METHOD = "Transference";
  private String transferenceType;

  public Transference(String invoiceID, double paidValue) {
    super(PAYMENT_METHOD, invoiceID, paidValue);
  }

  public Transference() {
    super();
  }

  @Override
  public Transference clone(String invoiceID, double paidValue) {
    return new Transference(invoiceID, paidValue);
  }

  public String getTransferenceType() {
    return transferenceType;
  }

  public void setTransferenceType(String transferenceType) {
    this.transferenceType = transferenceType;
  }
}
