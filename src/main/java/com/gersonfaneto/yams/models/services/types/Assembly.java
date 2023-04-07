package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.services.Service;
import java.util.LinkedList;
import java.util.List;

public class Assembly extends Service {

  private static final String SERVICE_TYPE = "Assembly";
  private final List<Component> usedComponents;

  public Assembly(String workOrderID, String serviceDescription) {
    super(workOrderID, SERVICE_TYPE, serviceDescription, 0.0);
    this.usedComponents = new LinkedList<>();
  }

  public Assembly() {
    super();
    this.usedComponents = new LinkedList<>();
  }

  @Override
  public Assembly clone(String workOrderID, String serviceDescription) {
    return new Assembly(workOrderID, serviceDescription);
  }

  public boolean addComponent(String componentID) {
    Component chosenComponent = DAO.fromComponents().findByID(componentID);

    if (chosenComponent == null) {
      return false;
    }

    DAO.fromComponents().deleteByID(componentID);

    usedComponents.add(chosenComponent);

    setServicePrice(getServicePrice() + chosenComponent.getComponentPrice());

    return true;
  }

  public boolean removeComponent(String componentID) {
    Component chosenComponent = usedComponents.stream()
        .filter(x -> x.getComponentID().equals(componentID))
        .findFirst()
        .orElse(null);

    if (chosenComponent == null) {
      return false;
    }

    usedComponents.remove(chosenComponent);
    setServicePrice(getServicePrice() - chosenComponent.getComponentPrice());

    DAO.fromComponents().createOne(chosenComponent);

    return true;
  }

  public List<Component> getUsedComponents() {
    return usedComponents;
  }
}
