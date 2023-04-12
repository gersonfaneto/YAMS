package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.users.InvalidPasswordException;
import com.gersonfaneto.yams.exceptions.users.PermissionDeniedException;
import com.gersonfaneto.yams.exceptions.users.UserAlreadyRegisteredException;
import com.gersonfaneto.yams.exceptions.users.UserNotFoundException;
import com.gersonfaneto.yams.exceptions.users.UserTypeNotFound;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;

public abstract class AuthController {

  public static User registerUser(
      User loggedUser,
      String userEmail,
      String userPassword,
      String technicianName,
      String userType
  ) throws UserAlreadyRegisteredException, UserTypeNotFound, PermissionDeniedException {
    if (loggedUser.getUserType() != UserType.Administrator) {
      throw new PermissionDeniedException("You don't have the needed privileges");
    }

    if (DAO.fromUsers().findByEmail(userEmail) != null) {
      throw new UserAlreadyRegisteredException("User already registered!");
    }
    if (UserType.findByName(userType) == null) {
      throw new UserTypeNotFound("User type not found!");
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

  public static User loginUser(
      String userEmail,
      String userPassword
  ) throws UserNotFoundException, InvalidPasswordException {
    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (foundUser == null) {
      throw new UserNotFoundException("User not registered!");
    }

    if (!userPassword.equals(foundUser.getUserPassword())) {
      throw new InvalidPasswordException("Password incorrect!");
    }

    return foundUser;
  }

  public static User updateInfo(
      String userEmail,
      String userPassword
  ) throws UserNotFoundException {
    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (foundUser == null) {
      throw new UserNotFoundException("User not registered!");
    }

    foundUser.setUserEmail(userEmail);
    foundUser.setUserPassword(userPassword);

    DAO.fromUsers().updateInformation(foundUser);

    return foundUser;
  }

  public static User unregisterUser(
      User loggedUser,
      String userEmail,
      String userPassword
  ) throws UserNotFoundException, InvalidPasswordException, PermissionDeniedException {
    if (loggedUser.getUserType() != UserType.Administrator) {
      throw new PermissionDeniedException("You don't have the needed privileges");
    }

    User foundUser = DAO.fromUsers().findByEmail(userEmail);

    if (foundUser == null) {
      throw new UserNotFoundException("User not registered!");
    }

    if (!userPassword.equals(foundUser.getUserPassword())) {
      throw new InvalidPasswordException("Password incorrect!");
    }

    DAO.fromUsers().deleteByID(foundUser.getUserID());

    return foundUser;
  }
}
