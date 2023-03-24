package com.gersonfaneto.techinfo.models.billing;

public class Payment {
    private static int referenceID = 0;
    private final int invoiceID;
    private final PaymentType paymentType;
    private final double payedValue;

    public Payment(PaymentType paymentType, double payedValue) {
        this.invoiceID = ++referenceID;
        this.paymentType = paymentType;
        this.payedValue = payedValue;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public double getPayedValue() {
        return payedValue;
    }
}
