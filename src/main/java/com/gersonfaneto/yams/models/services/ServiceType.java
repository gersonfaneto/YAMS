package com.gersonfaneto.yams.models.services;

public enum ServiceType {
  Cleaning("Cleaning", 70),
  ProgramInstallation("Program Installation", 10),
  Formatting("Formatting", 50),
  Assembly("Assembly", 0);

  private final String typeName;
  private final double typeValue;

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

  public static ServiceType findByName(String typeName) {
    for (ServiceType currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }
    return null;
  }
}
