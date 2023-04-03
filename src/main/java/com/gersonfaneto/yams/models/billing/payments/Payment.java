package com.gersonfaneto.yams.models.billing.payments;

public abstract class Payment {
    private String paymentID;
    private String invoiceID;
    private String paidValue;

    public Payment(String invoiceID, String paidValue) {
        this.invoiceID = invoiceID;
        this.paidValue = paidValue;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Payment otherPayment) {
            return otherPayment.paymentID.equals(this.paymentID);
        }

        return false;
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

    public String getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(String paidValue) {
        this.paidValue = paidValue;
    }
}
