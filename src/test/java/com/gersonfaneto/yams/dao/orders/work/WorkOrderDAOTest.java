package com.gersonfaneto.yams.dao.orders.work;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.WorkOrderState;
import com.gersonfaneto.yams.utils.Generators;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkOrderDAOTest {

  private final String randomTechnicianID = Generators.randomID();
  private final String randomClientID = Generators.randomID();
  private WorkOrder randomWorkOrder;

  @BeforeEach
  void setUp() {
    randomWorkOrder = new WorkOrder(randomClientID);
    randomWorkOrder.setTechnicianID(randomTechnicianID);
    DAO.fromWorkOrders().createOne(randomWorkOrder);

    for (int i = 0; i < 10; i++) {
      WorkOrder newWorkOrder = new WorkOrder(Generators.randomID());
      newWorkOrder.setTechnicianID(randomTechnicianID);
      DAO.fromWorkOrders().createOne(newWorkOrder);
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromWorkOrders().deleteMany();
  }

  // HACK: Find a better way of cleaning up these!
  @AfterAll
  static void cleanUp() {
    File dataFile = new File("data/work-orders.ser");

    dataFile.delete();
  }

  @Test
  void dataPersistence() {
    boolean hasSaved = ((Persist) DAO.fromWorkOrders()).saveAll();

    Assertions.assertTrue(hasSaved, "dataPersistence(): Failed to save data!");

    List<WorkOrder> beforeDeletion = DAO.fromWorkOrders().findMany();

    DAO.fromPurchaseOrders().deleteMany();

    boolean hasLoaded = ((Persist) DAO.fromWorkOrders()).loadAll();

    Assertions.assertTrue(hasLoaded, "dataPersistence(): Failed to load data!");

    List<WorkOrder> loadedWordOrders = DAO.fromWorkOrders().findMany();

    Assertions.assertEquals(11, loadedWordOrders.size());
    Assertions.assertEquals(beforeDeletion, loadedWordOrders);
  }

  @Test
  void createOne() {
    WorkOrder newWorkOrder = DAO.fromWorkOrders().createOne(
        new WorkOrder(
            Generators.randomID()
        )
    );

    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(newWorkOrder.getWorkOrderID());

    Assertions.assertEquals(newWorkOrder, foundWorkOrder);
  }

  @Test
  void findByID() {
    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(randomWorkOrder.getWorkOrderID());

    Assertions.assertEquals(randomWorkOrder, foundWorkOrder);
  }

  @Test
  void findMany() {
    List<WorkOrder> foundWorkOrder = DAO.fromWorkOrders().findMany();

    Assertions.assertEquals(11, foundWorkOrder.size());
  }

  @Test
  void updateInformation() {
    randomWorkOrder.setWorkOrderState(WorkOrderState.Open);

    boolean hasFound = DAO.fromWorkOrders().updateInformation(randomWorkOrder);
    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(randomWorkOrder.getWorkOrderID());

    Assertions.assertEquals(randomWorkOrder, foundWorkOrder);
  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromWorkOrders().deleteByID(randomWorkOrder.getWorkOrderID());

    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(randomWorkOrder.getWorkOrderID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(foundWorkOrder);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromWorkOrders().deleteMany();

    List<WorkOrder> foundWorkOrders = DAO.fromWorkOrders().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundWorkOrders.size());
  }

  @Test
  void findByClient() {
    List<WorkOrder> foundWordOrder = DAO.fromWorkOrders().findByClient(randomClientID);

    Assertions.assertEquals(1, foundWordOrder.size());
  }

  @Test
  void findByTechnician() {
    List<WorkOrder> foundWorkOrders = DAO.fromWorkOrders().findByTechnician(randomTechnicianID);

    Assertions.assertEquals(11, foundWorkOrders.size());
  }
}
