package com.gersonfaneto.yams.models.billing.payments;

/**
 * Represents the payments performed on an <code>Invoice</code>, after it's generated.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public class Payment {

  private String paymentID;
  private String invoiceID;
  private PaymentMethod paymentMethod;
  private double paidValue;

  /**
   * Constructs a new <code>Payment</code>.
   *
   * @param invoiceID The ID of the referent Invoice.
   * @param paymentMethod The chosen payment method.
   * @param paidValue The paid value.
   * @see PaymentMethod
   */
  public Payment(String invoiceID, PaymentMethod paymentMethod, double paidValue) {
    this.invoiceID = invoiceID;
    this.paymentMethod = paymentMethod;
    this.paidValue = paidValue;
  }

  /**
   * Compares an <code>Object</code> to the <code>Payment</code>.
   *
   * @param otherObject The <code>Object</code> to be compared to.
   * @return <code>true</code> if the objects match, or <code>false</code> if they don't.
   */
  @Override
  public boolean equals(Object otherObject) {
    // Checking if the Object passed isn't the Payment itself.
    if (this == otherObject) {
      return true;
    }

    // Checking if the Object passed is null.
    if (otherObject == null) {
      return false;
    }

    // Checking if the Object passed is from the Class Payment and casting it.
    if (!(otherObject instanceof Payment otherPayment)) {
      return false;
    }

    // Comparing by the IDs.
    return paymentID.equals(otherPayment.paymentID);
  }

  /**
   * Generate a <code>String</code> from the most important information of the <code>Payment</code>.
   *
   * @return Relevant information about the <code>Payment</code>.
   */
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
