package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import java.security.InvalidParameterException;

public abstract class AuthController {

  public static Technician registerUser(String userEmail, String userPassword,
      String technicianName) {
    if (DAO.fromTechnicians().findByEmail(userEmail) != null) {
      throw new InvalidParameterException("User already registered under!");
    }

    Technician newTechnician = new Technician(userEmail, userPassword, technicianName);
    DAO.fromTechnicians().createOne(newTechnician);

    return newTechnician;
  }

  public static Technician loginUser(String userEmail, String userPassword) {
    if (DAO.fromTechnicians().findByEmail(userEmail) == null) {
      throw new InvalidParameterException("User not registered!");
    }

    Technician foundTechnician = DAO.fromTechnicians().findByEmail(userEmail);

    if (!foundTechnician.getUserPassword().equals(userPassword)) {
      throw new InvalidParameterException("Incorrect password!");
    }

    return foundTechnician;
  }

  public static Technician updateInfo(String userEmail, String userPassword) {
    if (DAO.fromTechnicians().findByEmail(userEmail) == null) {
      throw new InvalidParameterException("User not registered!");
    }

    Technician foundTechnician = DAO.fromTechnicians().findByEmail(userEmail);

    foundTechnician.setUserEmail(userEmail);
    foundTechnician.setUserPassword(userPassword);

    DAO.fromTechnicians().updateInformation(foundTechnician);

    return foundTechnician;
  }

  public static Technician unregisterUser(String userEmail, String userPassword) {
    if (DAO.fromTechnicians().findByEmail(userEmail) == null) {
      throw new InvalidParameterException("User not registered!");
    }

    Technician foundTechnician = DAO.fromTechnicians().findByEmail(userEmail);

    DAO.fromTechnicians().deleteByID(foundTechnician.getUserID());

    return foundTechnician;
  }

}