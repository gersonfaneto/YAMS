package com.gersonfaneto.yams.builders.payment;

import com.gersonfaneto.yams.builders.Builder;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import java.security.InvalidParameterException;

public class PaymentBuilder implements Builder<Payment> {
  private String invoiceID;
  private PaymentMethod paymentMethod;
  private double paidValue;

  public PaymentBuilder() {

  }

  public PaymentBuilder fromInvoice(String invoiceID) {
    this.invoiceID = invoiceID;
    return this;
  }

  public PaymentBuilder defineMethod(String paymentMethod) {
    if (PaymentMethod.findByType(paymentMethod) == null) {
      throw new InvalidParameterException("Payment method not found!");
    }

    this.paymentMethod = PaymentMethod.findByType(paymentMethod);
    return this;
  }

  public PaymentBuilder ofValue(double paidValue) {
    this.paidValue = paidValue;
    return this;
  }

  @Override
  public Payment Build() {
    return new Payment(this);
  }

  public String getInvoiceID() {
    return invoiceID;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public double getPaidValue() {
    return paidValue;
  }
}
