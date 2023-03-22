package com.gersonfaneto.techinfo.models.billing;

import java.util.List;

public class Invoice {
    private final Integer invoiceID;
    private final Integer orderID;
    private List<Payment> receivedPayments;
    private Double totalValue;
    private Double payedValue;

    public Invoice(Integer invoiceID, Integer orderID, Double totalValue) {
        this.invoiceID = invoiceID;
        this.orderID = orderID;
        this.totalValue = totalValue;
    }

    public Payment newPayment() {
        return null;
    }
}
