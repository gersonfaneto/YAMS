package com.gersonfaneto.yams.models.billing;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.payment.Payment;
import com.gersonfaneto.yams.models.billing.payment.PaymentType;

import java.util.List;

public class Invoice {
    private final String serviceOrderID;
    private String invoiceID;
    private double totalValue;
    private double paidValue;

    public Invoice(String serviceOrderID, double totalValue) {
        this.invoiceID = "Undefined!";
        this.serviceOrderID = serviceOrderID;
        this.totalValue = totalValue;
        this.paidValue = 0.0;
    }

    public List<Payment> retrievePerformedPayment() {
        return DAO.fromPayments().findByInvoiceID(this.invoiceID);
    }

    public boolean newPayment(PaymentType paymentType, double paidValue) {
        if (this.paidValue + paidValue >= this.totalValue) {
            return false;
        }

        Payment newPayment = new Payment(this.invoiceID, paymentType, paidValue);

        DAO.fromPayments().createOne(newPayment);

        return true;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Order: %s
                Total Value: R$ %.2f
                Paid Value: R$ %.2f
                """, invoiceID, serviceOrderID, totalValue, paidValue);
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

    public String getServiceOrderID() {
        return serviceOrderID;
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