package com.gersonfaneto.yams.models.entities.user;

public abstract class User {
    private String userID;
    private String userEmail;
    private String userPassword;

    public User(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof User otherClient) {
            return otherClient.userID.equals(this.userID);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Email: %s
                Password: %s
                """, userID, userEmail, userPassword);
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
}
