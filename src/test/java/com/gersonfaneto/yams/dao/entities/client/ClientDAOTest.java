package com.gersonfaneto.yams.dao.entities.client;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.client.Client;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientDAOTest {

  private Client randomClient;

  @BeforeEach
  void setUp() {
    randomClient =
        DAO.fromClients()
            .createOne(new Client("Sherlock Holmes", "221B, Baker Street, London", "999-999-999"));

    for (int i = 0; i < 10; i++) {
      DAO.fromClients().createOne(new Client("John Doe", "Who knows?", "Doesn't matter..."));
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromClients().deleteMany();
  }

  // HACK: Find a better way of cleaning up these!
  @AfterAll
  static void cleanUp() {
    File dataFile = new File("data/clients.ser");

    dataFile.delete();
  }

  @Test
  void dataPersistence() {
    boolean hasSaved = DAO.fromClients().saveAll();

    Assertions.assertTrue(hasSaved, "dataPersistence(): Failed to save data!");

    List<Client> beforeDeletion = DAO.fromClients().findMany();

    DAO.fromClients().deleteMany();

    boolean hasLoaded = DAO.fromClients().loadAll();

    Assertions.assertTrue(hasLoaded, "dataPersistence(): Failed to load data!");

    List<Client> loadedClients = DAO.fromClients().findMany();

    Assertions.assertEquals(11, loadedClients.size());
    Assertions.assertEquals(beforeDeletion, loadedClients);
  }

  @Test
  void createOne() {
    Client newClient =
        DAO.fromClients()
            .createOne(new Client("John Watson", "221B, Baker Street, London", "999-999-999"));

    Client foundClient = DAO.fromClients().findByID(newClient.getClientID());

    Assertions.assertEquals(newClient, foundClient);
  }

  @Test
  void findByID() {
    Client foundClient = DAO.fromClients().findByID(randomClient.getClientID());

    Assertions.assertEquals(randomClient, foundClient);
  }

  @Test
  void findMany() {
    List<Client> foundClients = DAO.fromClients().findMany();

    Assertions.assertEquals(11, foundClients.size());
  }

  @Test
  void updateInformation() {
    randomClient.setHomeAddress("221A, Baker Street, London");

    boolean hasFound = DAO.fromClients().updateInformation(randomClient);
    Client foundClient = DAO.fromClients().findByID(randomClient.getClientID());

    Assertions.assertTrue(hasFound);
    Assertions.assertEquals("221A, Baker Street, London", foundClient.getHomeAddress());
  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromClients().deleteByID(randomClient.getClientID());

    Client foundClient = DAO.fromClients().findByID(randomClient.getClientID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(foundClient);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromClients().deleteMany();

    List<Client> foundClients = DAO.fromClients().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundClients.size());
  }

  @Test
  void findByName() {
    List<Client> foundClients = DAO.fromClients().findByName("John Doe");

    Assertions.assertEquals(10, foundClients.size());
  }
}
