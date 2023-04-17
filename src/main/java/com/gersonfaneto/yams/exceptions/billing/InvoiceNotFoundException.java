package com.gersonfaneto.yams.exceptions.billing;

import com.gersonfaneto.yams.dao.billing.invoice.InvoiceCRUD;
import com.gersonfaneto.yams.dao.billing.invoice.InvoiceMemoryDAO;
import com.gersonfaneto.yams.models.billing.invoice.Invoice;

/**
 * Thrown when a <code>Invoice</code> isn't found in the by the DAO.
 *
 * @see InvoiceMemoryDAO
 * @see InvoiceCRUD
 * @see Invoice
 */
public class InvoiceNotFoundException extends Exception {

  /**
   * Constructs a new <code>InvoiceNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom message to be displayed.
   */
  public InvoiceNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
