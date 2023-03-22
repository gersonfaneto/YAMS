package com.gersonfaneto.techinfo.models.billing;

public class Payment {
    private final Integer invoiceID;
    private PaymentType paymentType;
    private Double payedValue;

    public Payment(PaymentType paymentType, Double payedValue, Integer invoiceID) {
        this.paymentType = paymentType;
        this.payedValue = payedValue;
        this.invoiceID = invoiceID;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Double getPayedValue() {
        return payedValue;
    }

    public void setPayedValue(Double payedValue) {
        this.payedValue = payedValue;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }
}
