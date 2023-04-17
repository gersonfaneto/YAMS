package com.gersonfaneto.yams.models.entities.user;

/**
 * Represents the types of Users on the System.
 *
 * @see User
 */
public enum UserType {
  Receptionist("Receptionist"),
  Technician("Technician"),
  Administrator("Administrator");

  private final String typeName;

  /**
   * Generates a new <code>enum</code> item based on its name.
   *
   * @param typeName The name of the user type.
   */
  UserType(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  /**
   * Searches for a valid <code>UserType</code> by its name.
   *
   * @param typeName The user type name to be searched.
   * @return The proper <code>UserType</code>, if found, or <code>null</code>, if not.
   */
  public static UserType findByName(String typeName) {
    for (UserType currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }

    return null;
  }
}
