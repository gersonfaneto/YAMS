package com.gersonfaneto.yams.models.entities.user;

public enum UserType {
  Receptionist("Receptionist"),
  Technician("Technician"),
  Administrator("Administrator");

  private final String typeName;

  UserType(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  public static UserType findByName(String typeName) {
    for (UserType currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }

    return null;
  }
}
