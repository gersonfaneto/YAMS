package com.gersonfaneto.yams.models.orders.work.states;

public enum StateType {
  Canceled("Canceled"),
  Created("Created"),
  Finished("Finished"),
  Open("Open"),
  Payed("Payed");

  private final String typeName;

  StateType(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  public static StateType findByType(String typeName) {
    for (StateType currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }

    return null;
  }
}
