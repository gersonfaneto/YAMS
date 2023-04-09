package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.InvalidPasswordException;
import com.gersonfaneto.yams.exceptions.UserAlreadyRegisteredException;
import com.gersonfaneto.yams.exceptions.UserNotFoundException;
import com.gersonfaneto.yams.models.entities.technician.Technician;

public abstract class AuthController {

  public static Technician registerUser(
      String userEmail, String userPassword, String technicianName)
      throws UserAlreadyRegisteredException {
    if (DAO.fromTechnicians().findByEmail(userEmail) != null) {
      throw new UserAlreadyRegisteredException("User '" + userEmail + "' already registered!");
    }

    Technician newTechnician = new Technician(userEmail, userPassword, technicianName);
    DAO.fromTechnicians().createOne(newTechnician);

    return newTechnician;
  }

  public static Technician loginUser(String userEmail, String userPassword)
      throws UserNotFoundException, InvalidPasswordException {
    if (DAO.fromTechnicians().findByEmail(userEmail) == null) {
      throw new UserNotFoundException("User '" + userEmail + "' not registered!");
    }

    Technician foundTechnician = DAO.fromTechnicians().findByEmail(userEmail);

    if (!foundTechnician.getUserPassword().equals(userPassword)) {
      throw new InvalidPasswordException("Incorrect password for user " + userEmail + " !");
    }

    return foundTechnician;
  }

  public static Technician updateInfo(String userEmail, String userPassword)
      throws UserNotFoundException {
    if (DAO.fromTechnicians().findByEmail(userEmail) == null) {
      throw new UserNotFoundException("User '" + userEmail + "' not registered!");
    }

    Technician foundTechnician = DAO.fromTechnicians().findByEmail(userEmail);

    foundTechnician.setUserEmail(userEmail);
    foundTechnician.setUserPassword(userPassword);

    DAO.fromTechnicians().updateInformation(foundTechnician);

    return foundTechnician;
  }

  public static Technician unregisterUser(String userEmail, String userPassword)
      throws UserNotFoundException {
    if (DAO.fromTechnicians().findByEmail(userEmail) == null) {
      throw new UserNotFoundException("User '" + userEmail + "' not registered!");
    }

    Technician foundTechnician = DAO.fromTechnicians().findByEmail(userEmail);

    DAO.fromTechnicians().deleteByID(foundTechnician.getUserID());

    return foundTechnician;
  }
}
