package com.gersonfaneto.yams.models.billing;

import javax.crypto.spec.IvParameterSpec;

public class Invoice {

  private String invoiceID;
  private String workOrderID;
  private double totalValue;
  private double paidValue;

  public Invoice(String workOrderID, double totalValue) {
    this.workOrderID = workOrderID;
    this.totalValue = totalValue;
    this.paidValue = 0.0;
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
}
