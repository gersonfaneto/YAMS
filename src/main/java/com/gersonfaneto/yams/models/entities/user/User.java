package com.gersonfaneto.yams.models.entities.user;

import java.io.Serializable;

/**
 * Represents the Users of the System.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see UserType
 */
public abstract class User implements Serializable {

  private String userID;
  private String userName;
  private String userEmail;
  private String userPassword;
  private UserType userType;

  /**
   * Constructs a new <code>User</code>.
   *
   * @param userName The <code>User</code> name.
   * @param userEmail The <code>User</code> chosen email.
   * @param userPassword The <code>User</code> chose password.
   * @param userType The type of the <code>User</code>.
   * @see UserType
   */
  public User(String userName, String userEmail, String userPassword, UserType userType) {
    this.userName = userName;
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userType = userType;
  }

  /**
   * Compares an <code>Object</code> to the <code>User</code>.
   *
   * @param otherObject The <code>Object</code> to be compared to.
   * @return <code>true</code> if the objects match, or <code>false</code> if they don't.
   */
  @Override
  public boolean equals(Object otherObject) {
    // Checking if the Object passed isn't the User itself.
    if (this == otherObject) {
      return true;
    }

    // Checking if the Object passed isn't null.
    if (otherObject == null) {
      return false;
    }

    // Checking if the Object passed is from the Class User and casting it.
    if (!(otherObject instanceof User otherUser)) {
      return false;
    }

    // Comparing by the IDs.
    return userID.equals(otherUser.userID);
  }

  /**
   * Generate a <code>String</code> from the most important information of the <code>User</code>.
   *
   * @return Relevant information about the <code>User</code>.
   */
  @Override
  public String toString() {
    return String.format(
        """
        ID: %s
        Email: %s
        Password: %s
        """,
        userID, userEmail, userPassword);
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
