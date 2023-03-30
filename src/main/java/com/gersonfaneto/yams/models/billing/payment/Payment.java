package com.gersonfaneto.yams.models.billing.payment;

public class Payment {
    private final String invoiceID;
    private final PaymentType paymentType;
    private final double paidValue;
    private String paymentID;

    public Payment(String invoiceID, PaymentType paymentType, double paidValue) {
        this.paymentID = "Undefined!";
        this.invoiceID = invoiceID;
        this.paymentType = paymentType;
        this.paidValue = paidValue;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Invoice: %s
                Type: %s
                Paid Value: R$ %.2f
                """, paymentID, invoiceID, paymentType.getTypeName(), paidValue);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof Payment otherPayment) {
            return otherPayment.paymentID.equals(this.paymentID);
        }

        return false;
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public double getPaidValue() {
        return paidValue;
    }
}
