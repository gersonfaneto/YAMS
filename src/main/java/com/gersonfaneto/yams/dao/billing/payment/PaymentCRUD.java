package com.gersonfaneto.yams.dao.billing.payment;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.billing.payment.Payment;

import java.util.List;

/**
 * Extends the <code>CRUD</code> interface by adding operations specific to the <code>Payment</code>
 * models.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see Payment
 */
public interface PaymentCRUD extends CRUD<Payment> {

  /**
   * Searches for all the <code>Payment</code>s related to a given <code>Invoice</code>.
   *
   * @param invoiceID The ID of the targeted <code>Invoice</code>.
   * @return The list of all the found <code>Payment</code>s.
   */
  List<Payment> findByInvoice(String invoiceID);
}
