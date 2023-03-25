package com.gersonfaneto.techinfo.models.billing;

public class Invoice {
    private int invoiceID;
    private int orderID;
    private double totalValue;
    private double payedValue;

    public Invoice(int orderID, double totalValue) {
        this.orderID = orderID;
        this.totalValue = totalValue;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
}
