package com.gersonfaneto.yams.dao.services;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceMemoryDAOTest {

  private final String randomWorkOrderID = UUID.randomUUID().toString();
  private Service randomService;

  @BeforeEach
  void setUp() {
    randomService = new Service(
        ServiceType.Formatting,
        "Format and install Windows 11 :(",
        List.of()
    );
    randomService.setWorkOrderID(randomWorkOrderID);
    DAO.fromService().createOne(randomService);


    for (int i = 0; i < 10; i++) {
      DAO.fromService().createOne(
          new Service(
              ServiceType.ProgramInstallation,
              "Minecraft!",
              List.of()
          )
      );
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromService().deleteMany();
  }

  @Test
  void createOne() {
    Service newService = DAO.fromService().createOne(
        new Service(
            ServiceType.Cleaning,
            "The client dropped his computer in a honey bathtub!",
            List.of()
        )
    );

    Service foundService = DAO.fromService().findByID(newService.getServiceID());

    Assertions.assertEquals(newService, foundService);
  }

  @Test
  void findByID() {
    Service foundService = DAO.fromService().findByID(randomService.getServiceID());

    Assertions.assertEquals(randomService, foundService);
  }

  @Test
  void findMany() {
    List<Service> foundServices = DAO.fromService().findMany();

    Assertions.assertEquals(11, foundServices.size());
  }

  @Test
  void updateInformation() {
    randomService.setServiceDescription("Format and install Ubuntu LTS :)");

    boolean hasFound = DAO.fromService().updateInformation(randomService);
    Service foundService = DAO.fromService().findByID(randomService.getServiceID());

    Assertions.assertTrue(hasFound);
    Assertions.assertEquals("Format and install Ubuntu LTS :)",
        foundService.getServiceDescription());

  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromService().deleteByID(randomService.getServiceID());

    Service foundService = DAO.fromService().findByID(randomService.getServiceID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(foundService);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromService().deleteMany();

    List<Service> foundServices = DAO.fromService().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundServices.size());
  }

  @Test
  void findByType() {
    List<Service> foundServices = DAO.fromService().findByType(ServiceType.ProgramInstallation);

    Assertions.assertEquals(10, foundServices.size());
  }

  @Test
  void findByWorkOrder() {
    List<Service> foundServices = DAO.fromService().findByWorkOrder(randomWorkOrderID);

    Assertions.assertEquals(1, foundServices.size());
  }
}