package com.gersonfaneto.yams.models.billing;

import com.gersonfaneto.yams.models.billing.payments.Payment;
import com.gersonfaneto.yams.models.services.Service;

import java.util.LinkedList;
import java.util.List;

public class Invoice {
    private String invoiceID;
    private String workOrderID;
    private double totalValue;
    private double paidValue;
    private List<Payment> performedPayments;

    public Invoice(String workOrderID, double totalValue, double paidValue) {
        this.workOrderID = workOrderID;
        this.totalValue = totalValue;
        this.paidValue = paidValue;
        this.performedPayments = new LinkedList<>();
    }

    public boolean newPayment(Payment newPayment) {
        if (newPayment.getPaidValue() + paidValue <= totalValue) {
            performedPayments.add(newPayment);
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof  Invoice otherInvoice) {
            return otherInvoice.invoiceID.equals(this.invoiceID);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Work Order: %s
                Total Value: R$ %.2f
                Paid Value: R$ %.2f
                """, invoiceID, workOrderID, totalValue, paidValue);
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getWorkOrderID() {
        return workOrderID;
    }

    public void setWorkOrderID(String workOrderID) {
        this.workOrderID = workOrderID;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(double paidValue) {
        this.paidValue = paidValue;
    }

    public List<Payment> getPerformedPayments() {
        return performedPayments;
    }

    public void setPerformedPayments(List<Payment> performedPayments) {
        this.performedPayments = performedPayments;
    }
}
