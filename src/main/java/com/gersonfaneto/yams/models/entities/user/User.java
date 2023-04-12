package com.gersonfaneto.yams.models.entities.user;

public abstract class User {

  private String userID;
  private String userEmail;
  private String userPassword;
  private UserType userType;

  public User(String userEmail, String userPassword, UserType userType) {
    this.userEmail = userEmail;
    this.userPassword = userPassword;
    this.userType = userType;
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }

    if (!(otherObject instanceof User otherUser)) {
      return false;
    }

    return userID.equals(otherUser.userID);
  }

  @Override
  public String toString() {
    return String.format("""
            ID: %s
            Email: %s
            Password: %s
            """,
        userID, userEmail, userPassword
    );
  }

  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
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
