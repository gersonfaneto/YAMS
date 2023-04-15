package com.gersonfaneto.yams.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.users.InvalidPasswordException;
import com.gersonfaneto.yams.exceptions.users.PermissionDeniedException;
import com.gersonfaneto.yams.exceptions.users.UserAlreadyRegisteredException;
import com.gersonfaneto.yams.exceptions.users.UserNotFoundException;
import com.gersonfaneto.yams.exceptions.users.UserTypeNotFound;
import com.gersonfaneto.yams.models.entities.admnistrator.Administrator;
import com.gersonfaneto.yams.models.entities.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthControllerTest {

  private User systemAdministrator = Administrator.retrieveInstance(
      "jsmith@gmail.com",
      "password",
      "John Smith"
  );

  @BeforeEach
  void setUp() {
    try {
      AuthController.registerUser(
          systemAdministrator,
          "sholmes@gmail.com",
          "watson",
          "Sherlock Holmes",
          "Technician"
      );
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @AfterEach
  void tearDown() {
    try {
      AuthController.unregisterUser(
          systemAdministrator,
          "sholmes@gmail.com",
          "watson"
      );
    } catch (Exception e) {
      System.out.println(e.getMessage());;
    }
  }

  @Test
  void registerUser() throws UserTypeNotFound, PermissionDeniedException,
      UserAlreadyRegisteredException {
    AuthController.registerUser(
        systemAdministrator,
        "jwatson@gmail.com",
        "holmes",
        "John Watson",
        "Technician"
    );

    User foundUser = DAO.fromUsers().findByEmail("jwatson@gmail.com");

    assertNotNull(foundUser);
  }

  @Test
  void loginUser() throws UserNotFoundException, InvalidPasswordException {
    User foundUser = AuthController.loginUser("sholmes@gmail.com", "watson");

    assertNotNull(foundUser);
  }

  @Test
  void updateInfo() throws UserNotFoundException {
    User foundUser = AuthController.updateInfo("jwatson@gmail.com", "221b");

    assertNotNull(foundUser);

    assertEquals(foundUser.getUserPassword(), "221b");
  }

  @Test
  void unregisterUser()
      throws UserNotFoundException, InvalidPasswordException, PermissionDeniedException {
    AuthController.unregisterUser(
        systemAdministrator,
        "sholmes@gmail.com",
        "watson"
    );

    User foundUser = DAO.fromUsers().findByEmail("sholmes@gmail.com");

    assertNull(foundUser);
  }
}