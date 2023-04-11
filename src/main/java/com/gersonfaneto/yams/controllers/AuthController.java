package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.InvalidPasswordException;
import com.gersonfaneto.yams.exceptions.UserAlreadyRegisteredException;
import com.gersonfaneto.yams.exceptions.UserNotFoundException;
import com.gersonfaneto.yams.exceptions.UserTypeNotFound;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

public abstract class AuthController {

  public static User registerUser(
      String userEmail, String userPassword, String technicianName, String userType
  ) throws UserAlreadyRegisteredException, UserTypeNotFound {
    if (DAO.fromUsers().findByEmail(userEmail) != null) {
      throw new UserAlreadyRegisteredException("User '" + userEmail + "' already registered!");
    }
    if (UserType.findByName(userType) == null) {
      throw new UserTypeNotFound("User Type '" + userType + "' not found!");
    }

    Technician newUser = new Technician(
        userEmail,
        userPassword,
        UserType.findByName(userType),
        technicianName
    );
    DAO.fromUsers().createOne(newUser);

    return newUser;
  }

  public static User loginUser(String userEmail, String userPassword)
      throws UserNotFoundException, InvalidPasswordException {
    if (DAO.fromUsers().findByEmail(userEmail) == null) {
      throw new UserNotFoundException("User '" + userEmail + "' not registered!");
    }

    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (!foundUser.getUserPassword().equals(userPassword)) {
      throw new InvalidPasswordException("Incorrect password for user '" + userEmail + "' !");
    }

    return foundUser;
  }

  public static User updateInfo(String userEmail, String userPassword)
      throws UserNotFoundException {
    if (DAO.fromUsers().findByEmail(userEmail) == null) {
      throw new UserNotFoundException("User '" + userEmail + "' not registered!");
    }

    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    foundUser.setUserEmail(userEmail);
    foundUser.setUserPassword(userPassword);

    DAO.fromUsers().updateInformation(foundUser);

    return foundUser;
  }

  public static User unregisterUser(String userEmail, String userPassword)
      throws UserNotFoundException, InvalidPasswordException {
    if (DAO.fromUsers().findByEmail(userEmail) == null) {
      throw new UserNotFoundException("User '" + userEmail + "' not registered!");
    }

    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (!foundUser.getUserPassword().equals(userPassword)) {
      throw new InvalidPasswordException("Incorrect password for '" + userEmail + "' !");
    }

    DAO.fromUsers().deleteByID(foundUser.getUserID());

    return foundUser;
  }
}
