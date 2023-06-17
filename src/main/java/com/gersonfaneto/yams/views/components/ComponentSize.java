package com.gersonfaneto.yams.views.components;

public enum ComponentSize {
  Small("Small"),
  Medium("Medium"),
  Large("Large");

  private final String typeName;

  ComponentSize (String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  public static ComponentSize findByType(String typeName) {
    for (ComponentSize currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }

    return null;
  }
}
