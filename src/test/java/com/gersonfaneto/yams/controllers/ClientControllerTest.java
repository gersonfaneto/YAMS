package com.gersonfaneto.yams.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.client.ClientNotFoundException;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientControllerTest {

  private final Client randomClient = DAO.fromClients().createOne(
      new Client(
          "Sherlock Holmes",
          "221B, Baker Street, London",
          "999-888-777"
      )
  );

  @BeforeEach
  void setUp() {
    DAO.fromWorkOrders().createOne(
        new WorkOrder(
            randomClient.getClientID()
        )
    );

    DAO.fromClients().createOne(
        new Client(
            "Moriarty",
            "The Tower of London , London",
            "000-000-001"
        )
    );

    DAO.fromClients().createOne(
        new Client(
            "Moriarty",
            "The Tower of London , London",
            "000-000-002"
        )
    );
  }

  @AfterEach
  void tearDown() {
    DAO.fromClients().deleteMany();
  }

  @Test
  void registerClient() {
    Client randomClientA = ClientController.registerClient(
        "John Watson",
        "221B, Baker Street, London",
        "999-888-777"
    );

    Client randomClientB = ClientController.registerClient(
        "John Watson",
        "221A, Baker Street, London",
        "777-888-999"
    );

    Client foundClientA = DAO.fromClients().findByID(randomClientA.getClientID());
    Assertions.assertEquals(foundClientA, randomClientA);

    Client foundClientB = DAO.fromClients().findByID(randomClientB.getClientID());
    Assertions.assertEquals(foundClientB, randomClientB);

    List<Client> foundClients = DAO.fromClients().findByName("John Watson");
    Assertions.assertEquals(2, foundClients.size());
  }

  @Test
  void unregisterClient() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ClientController.unregisterClient(
          UUID.randomUUID().toString()
      );
    });

    try {
      ClientController.unregisterClient(randomClient.getClientID());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<Client> foundClients = DAO.fromClients().findByName("John Watson");

    Assertions.assertEquals(0, foundClients.size());
  }

  @Test
  void findClient() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ClientController.findClient(UUID.randomUUID().toString());
    });

    Client foundClient;
    try {
      foundClient = ClientController.findClient(randomClient.getClientID());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Assertions.assertEquals(randomClient, foundClient);
  }

  @Test
  void findClients() {
    List<Client> foundClients = ClientController.findClients("Moriarty");

    Assertions.assertEquals(2, foundClients.size());
  }

  @Test
  void updateInfo() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ClientController.updateInfo(
          UUID.randomUUID().toString(),
          "Sherlock Holmes",
          "321B, Baker Street, London",
          "999-111-3333"
      );
    });

    try {
      ClientController.updateInfo(
          randomClient.getClientID(),
          "Sherlock Watson",
          "321B, Baker Street, London",
          "999-111-3333"
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Client foundClient = DAO.fromClients().findByID(randomClient.getClientID());

    Assertions.assertEquals("Sherlock Watson", foundClient.getClientName());
    Assertions.assertEquals("321B, Baker Street, London", foundClient.getHomeAddress());
    Assertions.assertEquals("999-111-3333", foundClient.getPhoneNumber());
  }

  @Test
  void retrieveWorkOrders() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ClientController.retrieveWorkOrders(UUID.randomUUID().toString());
    });

    List<WorkOrder> foundWorkOrders;

    try {
      foundWorkOrders = ClientController.retrieveWorkOrders(randomClient.getClientID());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Assertions.assertEquals(1, foundWorkOrders.size());
  }
}