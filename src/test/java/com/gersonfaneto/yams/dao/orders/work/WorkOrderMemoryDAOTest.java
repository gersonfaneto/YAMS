package com.gersonfaneto.yams.dao.orders.work;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.states.Open;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WorkOrderMemoryDAOTest {

  private final String randomTechnicianID = UUID.randomUUID().toString();
  private final String randomClientID = UUID.randomUUID().toString();
  private WorkOrder randomWorkOrder;

  @BeforeEach
  void setUp() {
    randomWorkOrder = new WorkOrder(randomClientID);
    randomWorkOrder.setTechnicianID(randomTechnicianID);
    DAO.fromWorkOrders().createOne(randomWorkOrder);

    for (int i = 0; i < 10; i++) {
      WorkOrder newWorkOrder = new WorkOrder(UUID.randomUUID().toString());
      newWorkOrder.setTechnicianID(randomTechnicianID);
      DAO.fromWorkOrders().createOne(newWorkOrder);
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromWorkOrders().deleteMany();
  }


  @Test
  void dataPersistence() {
    ((Persist) DAO.fromWorkOrders()).saveAll();

    List<WorkOrder> beforeDeletion = DAO.fromWorkOrders().findMany();

    DAO.fromPurchaseOrders().deleteMany();

    ((Persist) DAO.fromWorkOrders()).loadAll();

    List<WorkOrder> loadedWordOrders = DAO.fromWorkOrders().findMany();

    Assertions.assertEquals(11, loadedWordOrders.size());
    Assertions.assertEquals(beforeDeletion, loadedWordOrders);
  }

  @Test
  void createOne() {
    WorkOrder newWorkOrder =
        DAO.fromWorkOrders().createOne(new WorkOrder(UUID.randomUUID().toString()));

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
    randomWorkOrder.setWorkOrderState(new Open(randomWorkOrder));

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
