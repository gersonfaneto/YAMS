package com.gersonfaneto.yams.controllers;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.exceptions.client.ClientNotFoundException;
import com.gersonfaneto.yams.exceptions.orders.UnsupportedOperationException;
import com.gersonfaneto.yams.exceptions.orders.WorkOrderNotFoundException;
import com.gersonfaneto.yams.exceptions.services.ServiceNotFoundException;
import com.gersonfaneto.yams.exceptions.services.ServiceTypeNotFoundException;
import com.gersonfaneto.yams.exceptions.users.UserNotFoundException;
import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.technician.states.Free;
import com.gersonfaneto.yams.models.entities.technician.states.Occupied;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.orders.work.states.Canceled;
import com.gersonfaneto.yams.models.orders.work.states.Created;
import com.gersonfaneto.yams.models.orders.work.states.Finished;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServicesControllerTest {

  private Client randomClient;
  private Technician randomTechnician;

  @BeforeEach
  void setUp() {
    randomClient = DAO.fromClients().createOne(
        new Client(
            "John Smith",
            "123, Lexington Avenue, New York",
            "123-321-000"
        )
    );

    randomTechnician = (Technician) DAO.fromUsers().createOne(
        new Technician(
            "sholmes@gmail.com",
            "watson",
            UserType.Technician,
            "Sherlock Holmes"
        )
    );

    WorkOrder randomWorkOrder = DAO.fromWorkOrders().createOne(
        new WorkOrder(randomClient.getClientID())
    );

    Component randomComponent = DAO.fromComponents().createOne(
        new Component(
            ComponentType.HD,
            "Samsung 500GB",
            10,
            10,
            30
        )
    );

    List<Component> usedComponents = new LinkedList<>(
        List.of(randomComponent)
    );

    Service randomServiceA = DAO.fromService().createOne(
        new Service(
            ServiceType.Assembly,
            "The client wants a new HD!",
            usedComponents
        )
    );
    randomServiceA.setWorkOrderID(randomWorkOrder.getWorkOrderID());

    Service randomServiceB = DAO.fromService().createOne(
        new Service(
            ServiceType.ProgramInstallation,
            "The client wants to have Minecraft installed on its computer!",
            List.of()
        )
    );
    randomServiceB.setComplete(true);
    randomServiceB.setWorkOrderID(randomWorkOrder.getWorkOrderID());

    DAO.fromService().updateInformation(randomServiceA);
    DAO.fromService().updateInformation(randomServiceB);
  }

  @AfterEach
  void tearDown() {
    DAO.fromWorkOrders().deleteMany();
    DAO.fromService().deleteMany();
    DAO.fromComponents().deleteMany();
  }

  @Test
  void createWorkOrder() {
    Assertions.assertThrows(ClientNotFoundException.class, () -> {
      ServicesController.createWorkOrder(
          UUID.randomUUID().toString(),
          DAO.fromService().findMany()
      );
    });

    WorkOrder workOrder;

    try {
      workOrder = ServicesController.createWorkOrder(
          randomClient.getClientID(),
          DAO.fromService().findMany()
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<WorkOrder> foundWorkWorkers = DAO.fromWorkOrders().findMany();
    WorkOrder foundWorkOrder = DAO.fromWorkOrders().findByID(workOrder.getWorkOrderID());

    Assertions.assertEquals(workOrder, foundWorkOrder);
    Assertions.assertEquals(2, foundWorkWorkers.size());
  }

  @Test
  void listWorkOrders() {
    List<WorkOrder> workOrders = DAO.fromWorkOrders().findMany();

    Assertions.assertEquals(1, workOrders.size());
  }

  @Test
  void createService() {
    Assertions.assertThrows(ServiceTypeNotFoundException.class, () -> {
      ServicesController.createService(
          "Playing",
          "You should be working!",
          List.of()
      );
    });

    Service newService;

    try {
      newService = ServicesController.createService(
          "Cleaning",
          "The client dropped its computer in a honey bathtub!",
          List.of()
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Service foundService = DAO.fromService().findByID(newService.getServiceID());

    Assertions.assertEquals(newService, foundService);
  }

  @Test
  void removeService() {
    WorkOrder randomWorkOrder = DAO.fromWorkOrders()
        .findByClient(randomClient.getClientID())
        .get(0);

    Service firstService = DAO.fromService()
        .findByType(ServiceType.Assembly)
        .get(0);
    Service secondService = DAO.fromService()
        .findByType(ServiceType.ProgramInstallation)
        .get(0);

    Assertions.assertThrows(ServiceNotFoundException.class, () -> {
      ServicesController.removeService(
          randomWorkOrder.getWorkOrderID(),
          UUID.randomUUID().toString()
      );
    });

    Assertions.assertThrows(WorkOrderNotFoundException.class, () -> {
      ServicesController.removeService(
          UUID.randomUUID().toString(),
          firstService.getServiceID());
    });

    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      ServicesController.removeService(
          randomWorkOrder.getWorkOrderID(),
          secondService.getServiceID()
      );
    });

    try {
      ServicesController.removeService(
          randomWorkOrder.getWorkOrderID(),
          firstService.getServiceID()
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<Service> foundServices = DAO.fromService()
        .findByWorkOrder(randomWorkOrder.getWorkOrderID());

    Assertions.assertEquals(1, foundServices.size());
  }

  @Test
  void listServices() {
    WorkOrder randomWorkOrder = DAO.fromWorkOrders()
        .findByClient(randomClient.getClientID())
        .get(0);

    Assertions.assertThrows(WorkOrderNotFoundException.class, () -> {
      ServicesController.listServices(
          UUID.randomUUID().toString()
      );
    });

    List<Service> foundServices;

    try {
      foundServices = ServicesController.listServices(randomWorkOrder.getWorkOrderID());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Assertions.assertEquals(2, foundServices.size());
  }

  @Test
  void markAsDone() {
    Assertions.assertThrows(ServiceNotFoundException.class, () -> {
      ServicesController.markAsDone(UUID.randomUUID().toString());
    });

    Service doneService;

    try {
      doneService = ServicesController.markAsDone(
          DAO.fromService().findByType(ServiceType.Assembly).get(0).getServiceID()
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    Service foundService = DAO.fromService().findByID(doneService.getServiceID());

    Assertions.assertEquals(doneService, foundService);
    Assertions.assertTrue(foundService.isComplete());
  }

  @Test
  void openWorkOrder() {
    WorkOrder randomWorkOrder = DAO.fromWorkOrders()
        .findByClient(randomClient.getClientID())
        .get(0);

    Assertions.assertThrows(UserNotFoundException.class, () -> {
      ServicesController.openWorkOrder(UUID.randomUUID().toString());
    });

    Assertions.assertThrows(WorkOrderNotFoundException.class, () -> {
      randomWorkOrder.setWorkOrderState(new Canceled(randomWorkOrder));
      ServicesController.openWorkOrder(
          randomTechnician.getUserID()
      );
    });
    randomWorkOrder.setWorkOrderState(new Created(randomWorkOrder));

    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      randomTechnician.setTechnicianState(new Occupied(randomTechnician, null));
      ServicesController.openWorkOrder(
          randomTechnician.getUserID()
      );
    });
    randomTechnician.setTechnicianState(new Free(randomTechnician));

    try {
      ServicesController.openWorkOrder(randomTechnician.getUserID());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<WorkOrder> foundWorkOrders = DAO.fromWorkOrders()
        .findByTechnician(randomTechnician.getUserID());

    Assertions.assertEquals(1, foundWorkOrders.size());
  }

  @Test
  void closeWorkOrder() {
    WorkOrder randomWorkOrder = DAO.fromWorkOrders()
        .findByClient(randomClient.getClientID())
        .get(0);

    Assertions.assertThrows(UserNotFoundException.class, () -> {
      ServicesController.closeWorkOrder(
          UUID.randomUUID().toString()
      );
    });

    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      ServicesController.closeWorkOrder(
          randomTechnician.getUserID()
      );
    });

    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      randomTechnician.openOrder(randomWorkOrder);
      DAO.fromService()
          .findByWorkOrder(randomWorkOrder.getWorkOrderID())
          .forEach(x -> x.setComplete(false));
      ServicesController.closeWorkOrder(
          randomTechnician.getUserID()
      );
    });

    DAO.fromService()
        .findByWorkOrder(randomWorkOrder.getWorkOrderID())
        .forEach(x -> x.setComplete(true));

    randomTechnician.openOrder(randomWorkOrder);

    try {
      ServicesController.closeWorkOrder(randomTechnician.getUserID());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<WorkOrder> foundWorkOrders = DAO.fromWorkOrders()
        .findByTechnician(randomTechnician.getUserID());
    Assertions.assertEquals(1, foundWorkOrders.size());

    WorkOrder recentWorkOrder = foundWorkOrders.get(0);
    Assertions.assertInstanceOf(Finished.class, recentWorkOrder.getWorkOrderState());
  }

  @Test
  void cancelWorkOrder() {
    WorkOrder randomWorkOrder = DAO.fromWorkOrders()
        .findByClient(randomClient.getClientID())
        .get(0);

    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      ServicesController.closeWorkOrder(
          randomTechnician.getUserID()
      );
    });

    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      randomTechnician.openOrder(randomWorkOrder);
      DAO.fromService()
          .findByWorkOrder(randomWorkOrder.getWorkOrderID())
          .forEach(x -> x.setComplete(false));
      ServicesController.closeWorkOrder(
          randomTechnician.getUserID()
      );
    });

    DAO.fromService()
        .findByWorkOrder(randomWorkOrder.getWorkOrderID())
        .forEach(x -> x.setComplete(true));

    randomTechnician.openOrder(randomWorkOrder);

    try {
      ServicesController.cancelWorkOrder(randomTechnician.getUserID());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    List<WorkOrder> foundWorkOrders = DAO.fromWorkOrders()
        .findByTechnician(randomTechnician.getUserID());
    Assertions.assertEquals(1, foundWorkOrders.size());

    WorkOrder recentWorkOrder = foundWorkOrders.get(0);
    Assertions.assertInstanceOf(Canceled.class, recentWorkOrder.getWorkOrderState());
  }
}