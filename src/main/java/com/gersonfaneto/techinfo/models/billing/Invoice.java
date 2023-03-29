package com.gersonfaneto.techinfo.models.billing;

import com.gersonfaneto.techinfo.dao.DAO;
import com.gersonfaneto.techinfo.models.billing.payment.Payment;

import java.util.List;

public class Invoice {
    private final String orderID;
    private String invoiceID;
    private double totalValue;
    private double paidValue;

    public Invoice(String orderID, double totalValue) {
        this.invoiceID = "Undefined!";
        this.orderID = orderID;
        this.totalValue = totalValue;
        this.paidValue = 0.0;
    }

    public List<Payment> retrievePerformedPayment() {
        return DAO.fromPayments().findByInvoiceID(this.invoiceID);
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Order: %s
                Total Value: R$ %.2f
                Paid Value: R$ %.2f
                """, invoiceID, orderID, totalValue, paidValue);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof Invoice otherInvoice) {
            return otherInvoice.invoiceID.equals(this.invoiceID);
        }

        return false;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getOrderID() {
        return orderID;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getPaidValue() {
        List<Payment> foundPayments = DAO.fromPayments().findByInvoiceID(this.invoiceID);
        double paidValue = 0.0;

        for (Payment currentPayment : foundPayments) {
            paidValue += currentPayment.getPaidValue();
        }

        this.paidValue = paidValue;

        return paidValue;
    }

    public void setPaidValue(double paidValue) {
        this.paidValue = paidValue;
    }
}
