package com.gersonfaneto.yams.models.entities.admnistrator;

import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

public class Administrator extends User {

  private String administratorName;
  private static Administrator singleInstance;

  private Administrator(
      String userEmail,
      String userPassword,
      UserType userType,
      String administratorName
  ) {
    super(userEmail, userPassword, userType);
    this.administratorName = administratorName;
  }

  public Administrator retrieveInstance(
      String userEmail,
      String userPassword,
      String administratorName
  ) {
    if (singleInstance == null) {
      return new Administrator(userEmail, userPassword, UserType.Administrator, administratorName);
    }

    return singleInstance;
  }

  public String getAdministratorName() {
    return administratorName;
  }

  public void setAdministratorName(String administratorName) {
    this.administratorName = administratorName;
  }
}
