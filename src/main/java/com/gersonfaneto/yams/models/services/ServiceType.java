package com.gersonfaneto.yams.models.services;

/**
 * Represents the types of Services offered by the Assistance, including their base values.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see Service
 */
public enum ServiceType {
  Cleaning("Cleaning", 70),
  ProgramInstallation("Program Installation", 10),
  Formatting("Formatting", 50),
  Assembly("Assembly", 0);

  private final String typeName;
  private final double typeValue;

  /**
   * Generates a new <code>enum</code> item based on its value and value.
   *
   * @param typeName The name of the service type.
   * @param typeValue The value for the service type.
   */
  ServiceType(String typeName, double typeValue) {
    this.typeName = typeName;
    this.typeValue = typeValue;
  }

  public String getTypeName() {
    return typeName;
  }

  public double getTypeValue() {
    return typeValue;
  }

  /**
   * Searches for a valid <code>PaymentMethod</code> by its name.
   *
   * @param typeName The payment method name to be searched.
   * @return The proper <code>PaymentMethod</code>, if found, or <code>null</code>, if not.
   */
  public static ServiceType findByName(String typeName) {
    for (ServiceType currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }
    return null;
  }
}
