package com.gersonfaneto.yams.models.entities.receptionist;

import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

public class Receptionist extends User {
  private String receptionistName;

  public Receptionist(String userEmail, String userPassword,
      UserType userType, String receptionistName) {
    super(userEmail, userPassword, userType);
    this.receptionistName = receptionistName;
  }


  public String getReceptionistName() {
    return receptionistName;
  }

  public void setReceptionistName(String receptionistName) {
    this.receptionistName = receptionistName;
  }
}
