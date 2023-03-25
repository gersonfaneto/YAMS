package com.gersonfaneto.techinfo.models.billing;

public class Payment {
    private PaymentType paymentType;
    private double payedValue;
    private int paymentID;
    private int invoiceID;

    public Payment(PaymentType paymentType, double payedValue, int invoiceID) {
        this.invoiceID = invoiceID;
        this.paymentType = paymentType;
        this.payedValue = payedValue;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getPayedValue() {
        return payedValue;
    }

    public void setPayedValue(double payedValue) {
        this.payedValue = payedValue;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
}
