package com.gersonfaneto.yams.exceptions.orders;

import com.gersonfaneto.yams.models.orders.work.WorkOrder;

/**
 * Thrown when the <code>WorkOrder</code> current <code>State</code> doesn't support a attempt
 * operation.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see WorkOrder
 * @see com.gersonfaneto.yams.models.orders.work.states.State
 */
public class UnsupportedOperationException extends Exception {

  /**
   * Constructs a new <code>UnsupportedOperationException</code> with a custom error message.
   *
   * @param errorMessage The custom error message to be displayed.
   */
  public UnsupportedOperationException(String errorMessage) {
    super(errorMessage);
  }
}
