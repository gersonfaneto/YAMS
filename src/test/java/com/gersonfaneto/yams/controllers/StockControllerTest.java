package com.gersonfaneto.yams.controllers;

import clcom.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.stock.ComponentTypeNotFoundException;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StockControllerTest {

  private Component randomComponent;
  private PurchaseOrder randomPurchaseOrder;

  @BeforeEach
  void setUp() {
    randomComponent = DAO.fromComponents().createOne(
        new Component(
            ComponentType.Motherboard,
            "Asus Prime H510M-E",
            10,
            40.00,
            100.00
        )
    );

    randomPurchaseOrder = DAO.fromPurchaseOrders().createOne(
        new PurchaseOrder(
            ComponentType.GraphicsCard,
            "Nvidia RTX 3090 TI",
            10,
            80.00,
            100.00
        )
    );

    DAO.fromComponents().createOne(
        new Component(
            ComponentType.Others,
            "Cooler Master AIO",
            2,
            20.00,
            45.00
        )
    );
  }

  @AfterEach
  void tearDown() {
    DAO.fromComponents().deleteMany();
  }

  @Test
  void listComponents() {
    Assertions.assertThrows(ComponentTypeNotFoundException.class, () -> {
      StockController.listComponents("Banana");
    }, "listComponents(): Expected ComponentTypeNotFoundException wasn't thrown!");

    List<Component> allComponents = StockController.listComponents();
    Assertions.assertEquals(
        2,
        allComponents.size(),
        "listComponents(): Amount of all Components found didn't match expected!"
    );

    List<Component> specificComponents = null;

    try {
      specificComponents = StockController.listComponents("Others");
    } catch (Exception e) {
      Assertions.fail("listComponents(): Unexpected Exception was thrown!");
    }

    Assertions.assertEquals(
        1,
        specificComponents.size(),
        "listComponents(): Amount of specific Components didn't match expected!"
    );
  }

  @Test
  void reserveComponent() {
    Component receivedComponent = StockController.reserveComponent(
        randomComponent.getComponentID()
    );

    Assertions.assertEquals(
        randomComponent,
        receivedComponent,
        "reserveComponent(): Failed to retrieve proper Component!"
    );
    Assertions.assertEquals(
        9,
        randomComponent.getAmountInStock(),
        "reserveComponent(): Failed to update amount in stock!"
    );
  }

  @Test
  void restoreComponent() {
    Component receivedComponent = StockController.reserveComponent(
        randomComponent.getComponentID()
    );

    StockController.restoreComponent(receivedComponent);

    Assertions.assertEquals(
        10,
        receivedComponent.getAmountInStock(),
        "restoreComponent(): Failed to update amount in stock!"
    );
  }

  @Test
  void buyComponent() {
    Assertions.assertThrows(ComponentTypeNotFoundException.class, () -> {
      StockController.buyComponent(
          "Grapes",
          "The green ones!",
          0.50,
          1.00,
          100
      );
    }, "buyComponent(): Expected ComponentTypeNotFoundException wasn't thrown!");

    try {
      StockController.buyComponent(
          "SSD",
          "Samsung EVO 960GB",
          30.00,
          14.00,
          20
      );
    } catch (Exception e) {
      Assertions.fail("buyComponent(): Unexpected Exception was thrown!");
    }

    List<PurchaseOrder> foundPurchaseOrder = DAO.fromPurchaseOrders().findByType(ComponentType.SSD);

    Assertions.assertEquals(
        1,
        foundPurchaseOrder.size(),
        "buyComponent(): Failed to create PurchaseOrder!"
    );
  }

  @Test
  void storeBoughtComponents() {
    StockController.storeBoughtComponents(randomPurchaseOrder);

    List<Component> foundComponents = DAO.fromComponents()
        .findByType(ComponentType.GraphicsCard);

    Assertions.assertEquals(
        1,
        foundComponents.size(),
        "storeBoughtComponents(): Failed to process PurchaseOrder!"
    );
  }
}