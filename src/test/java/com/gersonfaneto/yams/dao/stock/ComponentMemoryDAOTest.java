package com.gersonfaneto.yams.dao.stock;

import static org.junit.jupiter.api.Assertions.*;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComponentMemoryDAOTest {

  private Component randomComponent;

  @BeforeEach
  void setUp() {
    randomComponent =
        DAO.fromComponents()
            .createOne(
                new Component(ComponentType.GraphicsCard, "Nvidia RTX 4090 TI", 10, 30.00, 100.00));

    for (int i = 0; i < 10; i++) {
      DAO.fromComponents()
          .createOne(new Component(ComponentType.RAM, "Corsair Prime", 10, 15.00, 30.00));
    }
  }

  @AfterEach
  void tearDown() {
    DAO.fromComponents().deleteMany();
  }

  @Test
  void dataPersistence() {
    ((Persist) DAO.fromComponents()).saveAll();

    List<Component> beforeDeletion = DAO.fromComponents().findMany();

    DAO.fromComponents().deleteMany();

    ((Persist) DAO.fromComponents()).loadAll();

    List<Component> loadedComponents = DAO.fromComponents().findMany();

    Assertions.assertEquals(11, loadedComponents.size());
    Assertions.assertEquals(beforeDeletion, loadedComponents);
  }

  @Test
  void createOne() {
    Component newComponent =
        DAO.fromComponents()
            .createOne(new Component(ComponentType.Motherboard, "Asus Prime", 10, 30.00, 100.00));

    Component foundComponent = DAO.fromComponents().findByID(newComponent.getComponentID());

    Assertions.assertEquals(newComponent, foundComponent);
  }

  @Test
  void findByID() {
    Component foundComponent = DAO.fromComponents().findByID(randomComponent.getComponentID());

    Assertions.assertEquals(randomComponent, foundComponent);
  }

  @Test
  void findMany() {
    List<Component> foundComponents = DAO.fromComponents().findMany();

    Assertions.assertEquals(11, foundComponents.size());
  }

  @Test
  void updateInformation() {
    randomComponent.setAmountInStock(30);

    boolean hasFound = DAO.fromComponents().updateInformation(randomComponent);
    Component foundComponent = DAO.fromComponents().findByID(randomComponent.getComponentID());

    Assertions.assertTrue(hasFound);
    Assertions.assertEquals(30, foundComponent.getAmountInStock());
  }

  @Test
  void deleteByID() {
    boolean hasDeleted = DAO.fromComponents().deleteByID(randomComponent.getComponentID());

    Component foundComponent = DAO.fromComponents().findByID(randomComponent.getComponentID());

    Assertions.assertTrue(hasDeleted);
    Assertions.assertNull(foundComponent);
  }

  @Test
  void deleteMany() {
    boolean hasDeleted = DAO.fromComponents().deleteMany();

    List<Component> foundComponents = DAO.fromComponents().findMany();

    Assertions.assertTrue(hasDeleted);
    Assertions.assertEquals(0, foundComponents.size());
  }

  @Test
  void findByType() {
    List<Component> foundComponents = DAO.fromComponents().findByType(ComponentType.RAM);

    Assertions.assertEquals(10, foundComponents.size());
  }

  @Test
  void findEquals() {
    Component foundComponent = DAO.fromComponents().findEquals(randomComponent);

    Assertions.assertEquals(randomComponent, foundComponent);
  }
}
