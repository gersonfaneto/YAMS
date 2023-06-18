package com.gersonfaneto.yams.dao.services.service;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.services.service.Service;
import com.gersonfaneto.yams.models.services.service.ServiceType;
import com.gersonfaneto.yams.utils.Generators;
import java.io.File;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ServiceDAOTest {

  private final String randomWorkOrderID = Generators.randomID();
  private Service randomService;

  @BeforeEach
  void setUp() {
    randomService =
        new Service(ServiceType.Formatting, "Format and install Windows 11 :(", null, 1);
    randomService.setWorkOrderID(randomWorkOrderID);
    DAO.fromService().createOne(randomService);

    for (int i = 0; i < 10; i++) {
      DAO.fromService()
          .createOne(new Service(ServiceType.ProgramInstallation, "Minecraft!", null, 1));
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromService().deleteMany();
  }

  // HACK: Find a better way of cleaning up these!
  @AfterAll
  static void cleanUp() {
    File dataFile = new File("data/services.ser");

    dataFile.delete();
  }

  @Test
  void dataPersistence() {
    boolean hasSaved = DAO.fromService().saveAll();

    Assertions.assertTrue(hasSaved, "dataPersistence(): Failed to save data!");

    List<Service> beforeDeletion = DAO.fromService().findMany();

    DAO.fromService().deleteMany();

    boolean hasLoaded = DAO.fromService().loadAll();

    Assertions.assertTrue(hasLoaded, "dataPersistence(): Failed to load data!");

    List<Service> loadedServices = DAO.fromService().findMany();

    Assertions.assertEquals(11, loadedServices.size());
    Assertions.assertEquals(beforeDeletion, loadedServices);
  }

  @Test
  void createOne() {
    Service newService =
        DAO.fromService()
            .createOne(
                new Service(
                    ServiceType.Cleaning,
                    "The client dropped his computer in a honey bathtub!",
                    null,
                    1));

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
    Assertions.assertEquals(
        "Format and install Ubuntu LTS :)", foundService.getServiceDescription());
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
