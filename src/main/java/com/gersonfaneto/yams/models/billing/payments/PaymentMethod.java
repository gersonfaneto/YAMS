package com.gersonfaneto.yams.models.billing.payments;

public enum PaymentMethod {
  Cash("Cash"),
  CreditCard("CreditCard"),
  DebitCard("DebitCard"),
  Transference("Transference");

  private final String typeName;

  PaymentMethod(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  public static PaymentMethod findByType(String typeName) {
    for (PaymentMethod currentMethod : values()) {
      if (currentMethod.typeName.equals(typeName)) {
        return currentMethod;
      }
    }

    return null;
  }
}
