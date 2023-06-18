package com.gersonfaneto.yams.dao.stock;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import com.gersonfaneto.yams.utils.Generators;
import com.gersonfaneto.yams.utils.ObjectIO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>ComponentCRUD</code> and <code>CRUD</code> operations. Uses a
 * <code> HashMap</code> as a cache to store all the <code>Component</code>s during the execution of
 * the program and loads or unloads the contents of it into a file using an <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see ComponentCRUD
 * @see ObjectIO
 */
public class ComponentDiskDAO implements ComponentCRUD {

  private final Map<String, Component> storedComponents;
  private final ObjectIO<Component> componentObjectIO;

  /**
   * Constructs a new <code>{@link ComponentDiskDAO}</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public ComponentDiskDAO(String savePath) {
    this.storedComponents = new HashMap<>();
    this.componentObjectIO = new ObjectIO<>(savePath);
  }

  public boolean saveAll() {
    List<Component> toSave = storedComponents.values().stream().toList();

    return componentObjectIO.saveObjects(toSave);
  }

  public boolean loadAll() {
    List<Component> loadedComponents = componentObjectIO.loadObjects();

    if (loadedComponents == null) {
      return false;
    }

    for (Component currentComponent : loadedComponents) {
      storedComponents.put(currentComponent.getComponentID(), currentComponent);
    }

    return true;
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
