package com.gersonfaneto.yams.dao.stock;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>ComponentCRUD</code> and <code>CRUD</code> operations. Uses a
 * <code>HashMap</code> to store all the <code>Component</code>s.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see ComponentCRUD
 */
public class ComponentMemoryDAO implements ComponentCRUD {

  private final Map<String, Component> storedComponents;

  /** Constructs a new <code>{@link ComponentMemoryDAO}</code> */
  public ComponentMemoryDAO() {
    this.storedComponents = new HashMap<>();
  }

  @Override
  public boolean saveAll() {
    return false;
  }

  @Override
  public boolean loadAll() {
    return false;
  }

  @Override
  public Component createOne(Component newComponent) {
    String newID = Generators.randomID();

    newComponent.setComponentID(newID);

    storedComponents.put(newID, newComponent);

    return newComponent;
  }

  @Override
  public Component findByID(String targetID) {
    return storedComponents.get(targetID);
  }

  @Override
  public List<Component> findMany() {
    return storedComponents.values().stream().toList();
  }

  @Override
  public boolean updateInformation(Component updatedComponent) {
    String componentID = updatedComponent.getComponentID();

    if (storedComponents.containsKey(componentID)) {
      storedComponents.put(componentID, updatedComponent);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedComponents.containsKey(targetID)) {
      storedComponents.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedComponents.isEmpty()) {
      storedComponents.clear();
      return true;
    }

    return false;
  }

  @Override
  public List<Component> findByType(ComponentType componentType) {
    return storedComponents.values().stream()
        .filter(x -> x.getComponentType().equals(componentType))
        .toList();
  }

  @Override
  public Component findEquals(Object otherObject) {
    return storedComponents.values().stream().filter(otherObject::equals).findFirst().orElse(null);
  }
}
