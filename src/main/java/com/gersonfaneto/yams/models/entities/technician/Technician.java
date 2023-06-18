package com.gersonfaneto.yams.models.entities.technician;

import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

public class Technician extends User {

  private TechnicianStatus technicianStatus;

  public Technician(String userEmail, String userPassword, String technicianName) {
    super(technicianName, userEmail, userPassword, UserType.Technician);
    this.technicianStatus = TechnicianStatus.Free;
  }

  public TechnicianStatus getStatus() {
    return technicianStatus;
  }

  public void setStatus(TechnicianStatus technicianStatus) {
    this.technicianStatus = technicianStatus;
  }
}
