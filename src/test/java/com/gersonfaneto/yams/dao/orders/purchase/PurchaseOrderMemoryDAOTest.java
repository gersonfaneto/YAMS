package com.gersonfaneto.yams.dao.orders.purchase;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.orders.purchase.PurchaseOrder;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PurchaseOrderMemoryDAOTest {

  private PurchaseOrder randomPurchaseOrder;

  @BeforeEach
  void setUp() {
    randomPurchaseOrder = DAO.fromPurchaseOrders().createOne(
        new PurchaseOrder(
            ComponentType.Motherboard,
            "ASUS Prime",
            10,
            30.00,
            100.00
        )
    );

    for (int i = 0; i < 10; i++) {
      DAO.fromPurchaseOrders().createOne(
          new PurchaseOrder(
              ComponentType.RAM,
              "Corsair Prime",
              4,
              15.00,
              30.00
          )
      );
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromPurchaseOrders().deleteMany();
  }

  @Test
  void dataPersistence() {
    boolean hasSaved = ((Persist) DAO.fromPurchaseOrders()).saveAll();

    Assertions.assertTrue(hasSaved, "dataPersistence(): Failed to save data!");

    List<PurchaseOrder> beforeDeletion = DAO.fromPurchaseOrders().findMany();

    DAO.fromPurchaseOrders().deleteMany();

    boolean hasLoaded = ((Persist) DAO.fromPurchaseOrders()).loadAll();

    Assertions.assertTrue(hasLoaded, "dataPersistence(): Failed to load data!");

    List<PurchaseOrder> loadedPurchaseOrders = DAO.fromPurchaseOrders().findMany();

    Assertions.assertEquals(11, loadedPurchaseOrders.size());
    Assertions.assertEquals(beforeDeletion, loadedPurchaseOrders);
  }

  @Test
  void createOne() {
    PurchaseOrder newPurchaseOrder = DAO.fromPurchaseOrders().createOne(
        new PurchaseOrder(
            ComponentType.GraphicsCard,
            "Nvidia RTX 3090 TI",
            10,
            40.00,
            100.00
        )
    );

    PurchaseOrder foundPurchaseOrder = DAO.fromPurchaseOrders()
        .findByID(newPurchaseOrder.getPurchaseOrderID());

    Assertions.assertEquals(newPurchaseOrder, foundPurchaseOrder);
  }

  @Test
  void findByID() {
    PurchaseOrder foundPurchaseOrder = DAO.fromPurchaseOrders()
        .findByID(randomPurchaseOrder.getPurchaseOrderID());

    Assertions.assertEquals(randomPurchaseOrder, foundPurchaseOrder);
  }

  @Test
  void findMany() {
    List<PurchaseOrder> foundPurchaseOrders = DAO.fromPurchaseOrders().findMany();

    Assertions.assertEquals(11, foundPurchaseOrders.size());
  }

  @Test
  void updateInformation() {
    randomPurchaseOrder.setBoughtAmount(30);

    boolean hasFound = DAO.fromPurchaseOrders().updateInformation(randomPurchaseOrder);
    PurchaseOrder foundPurchaseOrder = DAO.fromPurchaseOrders()
        .findByID(randomPurchaseOrder.getPurchaseOrderID());

    Assertions.assertTrue(hasFound);
    Assertions.assertEquals(30, foundPurchaseOrder.getBoughtAmount());
  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromPurchaseOrders()
        .deleteByID(randomPurchaseOrder.getPurchaseOrderID());

    PurchaseOrder foundPurchaseOrder = DAO.fromPurchaseOrders()
        .findByID(randomPurchaseOrder.getPurchaseOrderID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(foundPurchaseOrder);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromPurchaseOrders().deleteMany();

    List<PurchaseOrder> foundPurchaseOrders = DAO.fromPurchaseOrders().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundPurchaseOrders.size());
  }

  @Test
  void findByType() {
    List<PurchaseOrder> foundPurchaseOrders = DAO.fromPurchaseOrders()
        .findByType(ComponentType.RAM);

    Assertions.assertEquals(10, foundPurchaseOrders.size());
  }
}
