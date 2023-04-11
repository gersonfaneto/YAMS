package com.gersonfaneto.yams.models.billing.payments;

public class Payment {

  private String paymentID;
  private String invoiceID;
  private PaymentMethod paymentMethod;
  private double paidValue;

  public Payment(String invoiceID, PaymentMethod paymentMethod, double paidValue) {
    this.invoiceID = invoiceID;
    this.paymentMethod = paymentMethod;
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
    return String.format(
        """
            ID: %s
            Method: %s
            Invoice: %s
            Value: R$ %.2f
            """,
        paymentID, paymentMethod, invoiceID, paidValue);
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

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public double getPaidValue() {
    return paidValue;
  }

  public void setPaidValue(double paidValue) {
    this.paidValue = paidValue;
  }
}
