package com.gersonfaneto.yams.dao.entities.user;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.receptionist.Receptionist;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserMemoryDAOTest {
  private User randomUser;

  @BeforeEach
  void setUp() {
    randomUser =
        DAO.fromUsers()
            .createOne(
                new Receptionist(
                    "sholmes@gmail.com", "watson", UserType.Receptionist, "Sherlock Holmes"));

    for (int i = 0; i < 10; i++) {
      DAO.fromUsers()
          .createOne(
              new Technician(
                  "jdoe" + ((Integer) i).toString() + "@gmail.com",
                  "jdoe@" + ((Integer) i).toString(),
                  UserType.Technician,
                  "John Doe"));
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromUsers().deleteMany();
  }

  @Test
  void createOne() {
    User randomUser =
        DAO.fromUsers()
            .createOne(
                new Technician(
                    "jsmith@gmail.com", "jsmith@2023", UserType.Technician, "John Smith"));

    User foundUser = DAO.fromUsers().findByID(randomUser.getUserID());

    Assertions.assertEquals(randomUser, foundUser);
  }

  @Test
  void findByID() {
    User foundUser = DAO.fromUsers().findByID(randomUser.getUserID());

    Assertions.assertEquals(randomUser, foundUser);
  }

  @Test
  void findMany() {
    List<User> foundUsers = DAO.fromUsers().findMany();

    Assertions.assertEquals(11, foundUsers.size());
  }

  @Test
  void updateInformation() {
    randomUser.setUserPassword("221B");

    boolean hasFound = DAO.fromUsers().updateInformation(randomUser);
    User foundUser = DAO.fromUsers().findByID(randomUser.getUserID());

    Assertions.assertTrue(hasFound);
    Assertions.assertEquals("221B", foundUser.getUserPassword());
  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromUsers().deleteByID(randomUser.getUserID());

    User foundUser = DAO.fromUsers().findByID(randomUser.getUserID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(foundUser);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromUsers().deleteMany();

    List<User> foundUsers = DAO.fromUsers().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundUsers.size());
  }

  @Test
  void findByEmail() {
    User foundUser = DAO.fromUsers().findByEmail(randomUser.getUserEmail());

    Assertions.assertEquals(randomUser, foundUser);
  }
}
