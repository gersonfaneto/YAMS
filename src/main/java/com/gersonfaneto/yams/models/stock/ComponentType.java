package com.gersonfaneto.yams.models.stock;

/**
 * Represents the types of Computer Components that the Assistance might have in Stock, including
 * their base values.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see Component
 */
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

  /**
   * Generates a new <code>enum</code> item based on its name and value.
   *
   * @param typeName The name of the component type.
   * @param typeValue The base value for the component type.
   */
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

  /**
   * Searches for a valid <code>ComponentType</code> by its name.
   *
   * @param typeName The component type name to be searched.
   * @return The proper <code>ComponentType</code>, if found, or <code>null</code>, if not.
   */
  public static ComponentType findByType(String typeName) {
    for (ComponentType currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }

    return null;
  }
}
