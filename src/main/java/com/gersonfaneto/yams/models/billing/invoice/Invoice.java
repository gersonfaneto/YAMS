package com.gersonfaneto.yams.models.billing.invoice;

/**
 * Represent a Invoice generated after a <code>WorkOrder</code> is completed by a <code>Technician
 * </code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public class Invoice {

  private String invoiceID;
  private String workOrderID;
  private double totalValue;

  /**
   * Constructs a new <code>Invoice</code>.
   *
   * @param workOrderID The ID referent to the <code>WorkOrder</code>.
   * @param totalValue The total value of the services in the <code>WorkOrder</code>;
   */
  public Invoice(String workOrderID, double totalValue) {
    this.workOrderID = workOrderID;
    this.totalValue = totalValue;
  }

  /**
   * Compares an <code>Object</code> to the <code>Invoice</code>.
   *
   * @param otherObject The <code>Object</code> to be compared to.
   * @return <code>true</code> if the objects match, or <code>false</code> if they don't.
   */
  @Override
  public boolean equals(Object otherObject) {
    // Checking if the Object passed isn't the Invoice itself.
    if (this == otherObject) {
      return true;
    }

    // Checking if the Object passed is null.
    if (otherObject == null) {
      return false;
    }

    // Checking if the Object passed is from the Class Invoice and casting it.
    if (!(otherObject instanceof Invoice otherInvoice)) {
      return false;
    }

    // Comparing by the IDs.
    return invoiceID.equals(otherInvoice.invoiceID);
  }

  /**
   * Generate a <code>String</code> from the most important information of the <code>Invoice</code>.
   *
   * @return Relevant information about the <code>Invoice</code>.
   */
  @Override
  public String toString() {
    return String.format(
        """
            ID: %s
            Work Order: %s
            Total Value: R$ %.2f
            """,
        invoiceID, workOrderID, totalValue);
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
