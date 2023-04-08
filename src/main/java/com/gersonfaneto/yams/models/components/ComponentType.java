package com.gersonfaneto.yams.models.components;

public enum ComponentType {
  RAM("RAM", 20),
  Motherboard("Motherboard", 100),
  PowerSupply("Power Supply", 30),
  GraphicsCard("Graphics Card", 100),
  HD("HD", 30),
  SSD("SSD", 30),
  Others("Others", 0);

  private final String typeName;
  private final double typeValue;

  ComponentType(String typeName, double typeValue) {
    this.typeName = typeName;
    this.typeValue = typeValue;
  }

  public String getTypeName() {
    return typeName;
  }

  public double getTypeValue() {
    return typeValue;
  }

  public static ComponentType findByType(String typeName) {
    for (ComponentType currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }

    return null;
  }
}

