package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class Transference extends Payment {
    public Transference(String invoiceID, double paidValue) {
        super(invoiceID, paidValue);
    }
}
