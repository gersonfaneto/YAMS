package com.gersonfaneto.yams.builders.service;

import static com.gersonfaneto.yams.models.services.ServiceType.Assembly;

import com.gersonfaneto.yams.builders.Builder;
import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.services.Service;
import com.gersonfaneto.yams.models.services.ServiceType;
import java.security.InvalidParameterException;
import java.util.List;

public class ServiceBuilder implements Builder<Service> {

  private String workOrderID;
  private ServiceType serviceType;
  private String serviceDescription;
  private double servicePrice;
  private List<Component> usedComponents;

  public ServiceBuilder() {

  }

  public ServiceBuilder fromOrder(String workOrderID) {
    this.workOrderID = workOrderID;
    return this;
  }

  public ServiceBuilder ofType(String serviceType) {
    if (ServiceType.findByName(serviceType) == null) {
      throw new InvalidParameterException("Service type not found!");
    }

    this.serviceType = ServiceType.findByName(serviceType);
    return this;
  }

  public ServiceBuilder defineDescription(String serviceDescription) {
    this.serviceDescription = serviceDescription;
    return this;
  }

  public ServiceBuilder definePrice(double servicePrice) {
    if (serviceType.equals(Assembly)) {
      this.servicePrice = usedComponents.stream()
          .map(Component::getComponentPrice)
          .reduce(0.0, Double::sum);
      return this;
    }

    this.servicePrice = servicePrice;
    return this;
  }

  public ServiceBuilder addComponent(Component chosenComponent) {
    if (!serviceType.equals(Assembly)) {
      throw new InvalidParameterException(
          "Cannot add component to service of type " + serviceType.getTypeName() + " !"
      );
    }

    usedComponents.add(chosenComponent);

    return this;
  }

  public Service Build() {
    return new Service(this);
  }

  public String getWorkOrderID() {
    return workOrderID;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public String getServiceDescription() {
    return serviceDescription;
  }

  public double getServicePrice() {
    return servicePrice;
  }

  public List<Component> getUsedComponents() {
    return usedComponents;
  }
}
