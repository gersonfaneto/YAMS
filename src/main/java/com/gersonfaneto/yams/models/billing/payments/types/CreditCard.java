package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class CreditCard extends Payment {
    public CreditCard(String invoiceID, double paidValue) {
        super(invoiceID, paidValue);
    }
}