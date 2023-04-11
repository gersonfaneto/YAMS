package com.gersonfaneto.yams.dao.components;

import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.components.ComponentType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ComponentMemoryDAO implements ComponentCRUD {

  private final Map<String, Component> storedComponents;

  public ComponentMemoryDAO() {
    this.storedComponents = new HashMap<>();
  }

  @Override
  public Component createOne(Component newComponent) {
    String newID = UUID.randomUUID().toString();

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
    return storedComponents.values()
        .stream()
        .filter(x -> x.getComponentType().equals(componentType))
        .toList();
  }

  @Override
  public Component findEquals(Object otherObject) {
    return storedComponents.values()
        .stream()
        .filter(x -> x.equals(otherObject))
        .findFirst()
        .orElse(null);
  }
}
