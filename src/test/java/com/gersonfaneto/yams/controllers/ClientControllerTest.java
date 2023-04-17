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

  private Client randomClient;

  @BeforeEach
  void setUp() {
    randomClient = DAO.fromClients().createOne(
        new Client(
            "Sherlock Holmes",
            "221B, Baker Street, London",
            "999-888-777"
        )
    );

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
    Client randomClient = ClientController.registerClient(
        "John Watson",
        "221B, Baker Street, London",
        "999-888-777"
    );

    Client foundClient = DAO.fromClients().findByID(randomClient.getClientID());
    Assertions.assertEquals(
        foundClient,
        randomClient,
        "registerClient(): Failed to register new Client!"
    );
  }

  @Test
  void unregisterClient() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ClientController.unregisterClient(
          UUID.randomUUID().toString()
      );
    }, "unregisterClient(): Expected ClientNotFoundException not thrown!");

    try {
      ClientController.unregisterClient(randomClient.getClientID());
    } catch (Exception e) {
      Assertions.fail("unregisterClient(): Unexpected Exception thrown!");
    }

    List<Client> foundClients = DAO.fromClients().findByName("John Watson");

    Assertions.assertEquals(
        0,
        foundClients.size(),
        "unregisterClient(): Failed to remove Client!"
    );
  }

  @Test
  void findClient() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ClientController.findClient(UUID.randomUUID().toString());
    }, "findClient(): Expected ClientNotFoundException not thrown!");

    Client foundClient = null;

    try {
      foundClient = ClientController.findClient(randomClient.getClientID());
    } catch (Exception e) {
      Assertions.fail("findClient(): Unexpected Exception thrown!");
    }

    Assertions.assertEquals(
        randomClient,
        foundClient,
        "findClient(): Failed to retrieve Client information!"
    );
  }

  @Test
  void findClients() {
    List<Client> foundClients = ClientController.findClients("Moriarty");

    Assertions.assertEquals(
        2,
        foundClients.size(),
        "foundClients(): Expected number of Clients doesn't match!"
    );
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
    }, "updateInfo(): Expected ClientNotFoundException not thrown!");

    try {
      ClientController.updateInfo(
          randomClient.getClientID(),
          "Sherlock Watson",
          "321B, Baker Street, London",
          "999-111-3333"
      );
    } catch (Exception e) {
      Assertions.fail("updateInfo(): Unexpected Exception thrown!");
    }

    Client foundClient = DAO.fromClients().findByID(randomClient.getClientID());

    Assertions.assertEquals(
        "321B, Baker Street, London",
        foundClient.getHomeAddress(),
        "updateInfo(): Failed to update information of Client!"
    );
  }

  @Test
  void retrieveWorkOrders() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ClientController.retrieveWorkOrders(UUID.randomUUID().toString());
    }, "retrieveWorkOrders(): Expected ClientNotFoundException not thrown!");

    List<WorkOrder> foundWorkOrders = null;

    try {
      foundWorkOrders = ClientController.retrieveWorkOrders(randomClient.getClientID());
    } catch (Exception e) {
      Assertions.fail("retrieveWorkOrders(): Unexpected Exception thrown!");
    }

    Assertions.assertEquals(
        1,
        foundWorkOrders.size(),
        "retrieveWorkOrders(): Amount of WorkOrders found didn't match expected!"
    );
  }
}