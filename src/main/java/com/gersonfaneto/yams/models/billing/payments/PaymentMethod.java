package com.gersonfaneto.yams.models.billing.payments;

/**
 * Represents the accepted payment methods by the assistance.
 *
 * @see Payment
 */
public enum PaymentMethod {
  Cash("Cash"),
  CreditCard("CreditCard"),
  DebitCard("DebitCard"),
  Transference("Transference");

  private final String typeName;

  /**
   * Generate a new <code>enum</code> item based on its name.
   *
   * @param typeName The name of the payment method.
   */
  PaymentMethod(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  /**
   * Searches for a valid <code>PaymentMethod</code> by its name.
   *
   * @param typeName The payment method name to be searched.
   * @return The proper <code>PaymentMethod</code>, if found, or <code>null</code>, if not.
   */
  public static PaymentMethod findByType(String typeName) {
    for (PaymentMethod currentMethod : values()) {
      if (currentMethod.typeName.equals(typeName)) {
        return currentMethod;
      }
    }

    return null;
  }
}
