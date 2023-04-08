package com.gersonfaneto.yams.models.services;

import com.gersonfaneto.yams.builders.service.ServiceBuilder;
import com.gersonfaneto.yams.models.components.Component;
import java.security.Provider;
import java.util.List;

public class Service {

  private String serviceID;
  private String workOrderID;
  private ServiceType serviceType;
  private String serviceDescription;
  private double clientRating;
  private double servicePrice;
  private boolean isComplete = false;
  private List<Component> usedComponents;

  public Service(ServiceBuilder serviceBuilder) {
    this.workOrderID = serviceBuilder.getWorkOrderID();
    this.serviceType = serviceBuilder.getServiceType();
    this.serviceDescription = serviceBuilder.getServiceDescription();
    this.servicePrice = serviceBuilder.getServicePrice();
    this.usedComponents = serviceBuilder.getUsedComponents();
  }

  @Override
  public boolean equals(Object otherObject) {
    if (this == otherObject) {
      return true;
    }

    if (otherObject == null) {
      return false;
    }

    if (!(otherObject instanceof Service otherService)) {
      return false;
    }

    return serviceID.equals(otherService.serviceID);
  }

  @Override
  public String toString() {
    return String.format("""
            ID: %s
            Type: %s
            Description: %s
            Price: R$ %.2f
            Status: %s
            """, serviceID, serviceType, serviceDescription, servicePrice,
        (isComplete) ? "Complete" : "Pending");
  }

  public String getServiceID() {
    return serviceID;
  }

  public void setServiceID(String serviceID) {
    this.serviceID = serviceID;
  }

  public String getWorkOrderID() {
    return workOrderID;
  }

  public void setWorkOrderID(String workOrderID) {
    this.workOrderID = workOrderID;
  }

  public ServiceType getServiceType() {
    return serviceType;
  }

  public void setServiceType(ServiceType serviceType) {
    this.serviceType = serviceType;
  }

  public String getServiceDescription() {
    return serviceDescription;
  }

  public void setServiceDescription(String serviceDescription) {
    this.serviceDescription = serviceDescription;
  }

  public double getClientRating() {
    return clientRating;
  }

  public void setClientRating(double clientRating) {
    this.clientRating = clientRating;
  }

  public double getServicePrice() {
    return servicePrice;
  }

  public void setServicePrice(double servicePrice) {
    this.servicePrice = servicePrice;
  }

  public boolean isComplete() {
    return isComplete;
  }

  public void setComplete(boolean complete) {
    isComplete = complete;
  }

  public List<Component> getUsedComponents() {
    return usedComponents;
  }

  public void setUsedComponents(
      List<Component> usedComponents) {
    this.usedComponents = usedComponents;
  }
}
