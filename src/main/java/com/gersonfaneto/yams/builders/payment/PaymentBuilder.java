package com.gersonfaneto.yams.builders.payment;

import com.gersonfaneto.yams.builders.Builder;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;
import java.security.InvalidParameterException;

public class PaymentBuilder implements Builder<Payment> {
  private final PaymentMethod paymentMethod;
  private String invoiceID;
  private double paidValue;

  public PaymentBuilder(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public PaymentBuilder fromInvoice(String invoiceID) {
    this.invoiceID = invoiceID;
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
