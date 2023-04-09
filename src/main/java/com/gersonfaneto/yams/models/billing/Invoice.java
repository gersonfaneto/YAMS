package com.gersonfaneto.yams.models.billing;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.billing.payments.Payment;
import java.util.List;

public class Invoice {

  private String invoiceID;
  private String workOrderID;
  private double totalValue;

  public Invoice(String workOrderID, double totalValue) {
    this.workOrderID = workOrderID;
    this.totalValue = totalValue;
  }

  public double calculatePaidValue() {
    return DAO.fromPayments().findByInvoice(invoiceID).stream()
        .map(Payment::getPaidValue)
        .reduce(0.0, Double::sum);
  }

  public List<Payment> retrievePayments() {
    return DAO.fromPayments().findByInvoice(invoiceID);
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }

    if (!(otherObject instanceof Invoice otherInvoice)) {
      return false;
    }

    return invoiceID.equals(otherInvoice.invoiceID);
  }

  @Override
  public String toString() {
    return String.format(
        """
        ID: %s
        Work Order: %s
        Total Value: R$ %.2f
        Paid Value: R$ %.2f
        """,
        invoiceID, workOrderID, totalValue, calculatePaidValue());
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
}
