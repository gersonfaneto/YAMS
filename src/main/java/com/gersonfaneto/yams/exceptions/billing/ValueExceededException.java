package com.gersonfaneto.yams.exceptions.billing;

import com.gersonfaneto.yams.models.billing.invoice.Invoice;
import com.gersonfaneto.yams.models.billing.payments.Payment;

/**
 * Thrown when a value of a <code>Payment</code> exceeds the total value of its <code>Invoice</code>
 * .
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see Payment
 * @see Invoice
 */
public class ValueExceededException extends Exception {

  /**
   * Constructs a new <code>ValueExceededException</code> with a custom error message.
   *
   * @param errorMessage The custom message to be displayed.
   */
  public ValueExceededException(String errorMessage) {
    super(errorMessage);
  }
}
