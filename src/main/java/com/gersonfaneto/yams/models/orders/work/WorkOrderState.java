package com.gersonfaneto.yams.models.orders.work;

public enum WorkOrderState {
  Canceled("Canceled"),
  Created("Created"),
  Finished("Finished"),
  Open("Open"),
  Payed("Payed");

  private final String typeName;

  WorkOrderState(String typeName) {
    this.typeName = typeName;
  }

  public String getTypeName() {
    return typeName;
  }

  public static WorkOrderState findByType(String typeName) {
    for (WorkOrderState currentType : values()) {
      if (currentType.typeName.equals(typeName)) {
        return currentType;
      }
    }

    return null;
  }
}
