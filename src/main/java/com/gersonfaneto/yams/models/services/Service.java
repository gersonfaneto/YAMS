package com.gersonfaneto.yams.models.services;

public abstract class Service {

  private String serviceID;
  private String workOrderID;
  private String serviceType;
  private String serviceDescription;
  private double clientRating;
  private double servicePrice;
  private boolean isComplete;

  public Service(String workOrderID, String serviceType, String serviceDescription,
      double servicePrice) {
    this.workOrderID = workOrderID;
    this.serviceType = serviceType;
    this.serviceDescription = serviceDescription;
    this.clientRating = 0.0;
    this.servicePrice = servicePrice;
    this.isComplete = false;
  }

  public Service() {

  }

  public abstract Service clone(String workOrderID, String serviceDescription);

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

  public String getServiceType() {
    return serviceType;
  }

  public void setServiceType(String serviceType) {
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
}
