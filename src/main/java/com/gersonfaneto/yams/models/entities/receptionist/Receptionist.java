package com.gersonfaneto.yams.models.entities.receptionist;

import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

/**
 * Represents the Receptionists of the Assistance.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public class Receptionist extends User {

  private String receptionistName;

  /**
   * Constructs a new <code>Receptionist</code>.
   *
   * @param userEmail The <code>User</code> chosen email.
   * @param userPassword The <code>User</code> chosen password.
   * @param userType The type of the <code>User</code>.
   * @param receptionistName The name of the <code>Receptionist</code>.
   * @see User
   */
  public Receptionist(
      String userEmail, String userPassword, UserType userType, String receptionistName) {
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
