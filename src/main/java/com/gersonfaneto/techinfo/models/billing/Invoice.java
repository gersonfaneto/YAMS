package com.gersonfaneto.techinfo.models.billing;

import java.util.List;

public class Invoice {
    private final Integer invoiceID;
    private final Integer orderID;
    private List<Payment> receivedPayments;
    private Double totalValue;
    private Double payedValue;

    public Invoice(Double totalValue, Integer orderID, Integer invoiceID) {
        this.totalValue = totalValue;
        this.orderID = orderID;
        this.invoiceID = invoiceID;
    }

    public Payment newPayment() {
        return null;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public List<Payment> getReceivedPayments() {
        return receivedPayments;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Double getPayedValue() {
        return payedValue;
    }

    public void setPayedValue(Double payedValue) {
        this.payedValue = payedValue;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

}
