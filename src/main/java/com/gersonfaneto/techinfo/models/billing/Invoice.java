package com.gersonfaneto.techinfo.models.billing;

import java.util.List;

public class Invoice {
    private static int referenceID = 0;
    private final int invoiceID;
    private final int orderID;
    private double totalValue;
    private double payedValue;
    private List<Payment> receivedPayments;

    public Invoice(int orderID, double totalValue) {
        this.invoiceID = ++referenceID;
        this.orderID = orderID;
        this.totalValue = totalValue;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getPayedValue() {
        return payedValue;
    }

    public void setPayedValue(double payedValue) {
        this.payedValue = payedValue;
    }

    public List<Payment> getReceivedPayments() {
        return receivedPayments;
    }
}
