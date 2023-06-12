package com.gersonfaneto.yams.models.entities.technician;

public enum TechnicianStatus {
  Free("Free"),
  Occupied("Occupied");

  private final String statusName;

  private TechnicianStatus(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return statusName;
  }

  public static TechnicianStatus findByType(String statusName) {
    for (TechnicianStatus technicianStatus : values()) {
      if (technicianStatus.statusName.equals(statusName)) {
        return technicianStatus;
      }
    }

    return null;
  }
}
