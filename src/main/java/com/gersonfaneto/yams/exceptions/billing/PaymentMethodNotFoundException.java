package com.gersonfaneto.yams.exceptions.billing;

import com.gersonfaneto.yams.models.billing.payments.PaymentMethod;

/**
 * Thrown when a given <code>PaymentMethod</code> doesn't match any of the ones declared under the
 * <code>PaymentMethod</code> enumeration.
 *
 * @see PaymentMethod
 */
public class PaymentMethodNotFoundException extends Exception {

  /**
   * Constructs a new <code>PaymentMethodNotFoundException</code> with a custom error message.
   *
   * @param errorMessage The custom message to be displayed.
   */
  public PaymentMethodNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
