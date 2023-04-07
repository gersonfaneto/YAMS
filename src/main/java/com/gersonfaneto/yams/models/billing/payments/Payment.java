package com.gersonfaneto.yams.models.billing.payments;

public abstract class Payment {

  private String paymentID;
  private String invoiceID;
  private double paidValue;

  public Payment(String invoiceID, double paidValue) {
    this.invoiceID = invoiceID;
    this.paidValue = paidValue;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }

    if (!(otherObject instanceof Payment otherPayment)) {
      return false;
    }

    return paymentID.equals(otherPayment.paymentID);
  }

  @Override
  public String toString() {
    return String.format("""
        ID: %s
        Invoice: %s
        Value: R$ %.2f
        """, paymentID, invoiceID, paidValue);
  }

  public String getPaymentID() {
    return paymentID;
  }

  public void setPaymentID(String paymentID) {
    this.paymentID = paymentID;
  }

  public String getInvoiceID() {
    return invoiceID;
  }

  public void setInvoiceID(String invoiceID) {
    this.invoiceID = invoiceID;
  }

  public double getPaidValue() {
    return paidValue;
  }

  public void setPaidValue(double paidValue) {
    this.paidValue = paidValue;
  }
}
