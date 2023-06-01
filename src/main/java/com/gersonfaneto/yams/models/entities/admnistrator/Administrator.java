package com.gersonfaneto.yams.models.entities.admnistrator;

import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

/**
 * Represents the Administrator of the System using the "Singleton" pattern to ensure there is only
 * one.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 */
public class Administrator extends User {

  private String administratorName;
  private static Administrator singleInstance;

  /**
   * Constructs the Administrator.
   *
   * @param userEmail         The <code>User</code> chosen email.
   * @param userPassword      The <code>User</code> chosen password.
   * @param userType          The type of the <code>User</code>.
   * @param administratorName The name of the <code>Administrator</code>
   * @see User
   * @see UserType
   */
  private Administrator(
      String userEmail,
      String userPassword,
      String administratorName
  ) {
    super(userEmail, userPassword, UserType.Administrator);
    this.administratorName = administratorName;
  }

  /**
   * Retrieves the <code>Administrator</code> instance, creating it if needed.
   *
   * @param userEmail         The <code>User</code> chosen email.
   * @param userPassword      The <code>User</code> chosen password.
   * @param administratorName The name of the <code>Administrator</code>.
   * @return The unique instance of the Class.
   */
  public static Administrator retrieveInstance(
      String userEmail, String userPassword, String administratorName) {
    if (singleInstance == null) {
      return new Administrator(userEmail, userPassword, administratorName);
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
