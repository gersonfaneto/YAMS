package com.gersonfaneto.techinfo.models.billing;

public class Payment {
    private final Integer invoiceID;
    private PaymentType paymentType;
    private Double payedValue;

    public Payment(Integer invoiceID, PaymentType paymentType, Double payedValue) {
        this.invoiceID = invoiceID;
        this.paymentType = paymentType;
        this.payedValue = payedValue;
    }
}
