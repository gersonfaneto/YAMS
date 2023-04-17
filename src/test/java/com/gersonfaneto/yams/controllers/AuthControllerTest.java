package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.users.InvalidPasswordException;
import com.gersonfaneto.yams.exceptions.users.PermissionDeniedException;
import com.gersonfaneto.yams.exceptions.users.UserAlreadyRegisteredException;
import com.gersonfaneto.yams.exceptions.users.UserNotFoundException;
import com.gersonfaneto.yams.exceptions.users.UserTypeNotFoundException;
import com.gersonfaneto.yams.models.entities.admnistrator.Administrator;
import com.gersonfaneto.yams.models.entities.receptionist.Receptionist;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthControllerTest {

  private User systemAdministrator;
  private User randomUser;
  
  @BeforeEach
  void setUp() {
    systemAdministrator = Administrator.retrieveInstance(
        "jsmith@gmail.com",
        "password",
        "John Smith"
    );

    randomUser = new Receptionist(
        "jdoe@gmail.com",
        "1234",
        UserType.Technician,
        "John Doe"
    );

    User newUser = new Technician(
        "sholmes@gmail.com",
        "watson",
        UserType.Technician,
        "Sherlock Holmes"
    );

    DAO.fromUsers().createOne(newUser);
  }

  @AfterEach
  void tearDown() {
    DAO.fromUsers().deleteMany();
  }

  @Test
  void registerUser() {
    Assertions.assertDoesNotThrow(() -> {
      AuthController.registerUser(
          systemAdministrator,
          "jwatson@gmail.com",
          "holmes",
          "John Watson",
          "Technician"
      );
    });

    Assertions.assertThrows(UserAlreadyRegisteredException.class, () -> {
      AuthController.registerUser(
          systemAdministrator,
          "sholmes@gmail.com",
          "watson",
          "Technician",
          "Sherlock Holmes"
      );
    });

    Assertions.assertThrows(UserTypeNotFoundException.class, () -> {
      AuthController.registerUser(
          systemAdministrator,
          "mholmes@gmail.com",
          "eurus",
          "Mycroft Holmes",
          "Brother"
      );
    });

    Assertions.assertThrows(PermissionDeniedException.class, () -> {
      AuthController.registerUser(
          randomUser,
          "moriarty@gmail.com",
          "stayingalive",
          "Moriarty",
          "Receptionist"
      );
    });

    User foundUser = DAO.fromUsers().findByEmail("jwatson@gmail.com");
    List<User> registeredUsers = DAO.fromUsers().findMany();

    Assertions.assertNotNull(foundUser);
    Assertions.assertEquals(registeredUsers.size(), 2);
  }

  @Test
  void loginUser() {
    Assertions.assertThrows(UserNotFoundException.class, () -> {
      AuthController.loginUser(
          "mholmes@gmail.com",
          "eurus"
      );
    });

    Assertions.assertThrows(InvalidPasswordException.class, () -> {
      AuthController.loginUser(
          "sholmes@gmail.com",
          "mycroft"
      );
    });

    User foundUser = null;

    try {
      foundUser = AuthController.loginUser("sholmes@gmail.com", "watson");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Assertions.assertNotNull(foundUser);
  }

  @Test
  void updateInfo() {
    Assertions.assertThrows(UserNotFoundException.class, () -> {
      AuthController.updateInfo("mholmes@gmail.com", "eurus");
    });

    User foundUser;

    try {
      foundUser = AuthController.updateInfo("sholmes@gmail.com", "221b");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Assertions.assertNotNull(foundUser);
    Assertions.assertEquals(foundUser.getUserPassword(), "221b");
  }

  @Test
  void unregisterUser() {
    Assertions.assertThrows(UserNotFoundException.class, () -> {
      AuthController.unregisterUser(systemAdministrator, "mholmes@gmail.com");
    });

    Assertions.assertThrows(PermissionDeniedException.class, () -> {
      AuthController.unregisterUser(randomUser, "mholmes@gmail.com");
    });

    try {
      AuthController.unregisterUser(systemAdministrator, "sholmes@gmail.com");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    User foundUser = DAO.fromUsers().findByEmail("sholmes@gmail.com");

    Assertions.assertNull(foundUser);
  }
}