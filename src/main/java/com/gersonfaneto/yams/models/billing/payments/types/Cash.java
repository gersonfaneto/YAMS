package com.gersonfaneto.yams.models.billing.payments.types;

import com.gersonfaneto.yams.models.billing.payments.Payment;

public class Cash extends Payment {
    public Cash(String invoiceID, String paidValue) {
        super(invoiceID, paidValue);
    }
}
