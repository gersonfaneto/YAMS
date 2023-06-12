package com.gersonfaneto.yams.models.services;

import static com.gersonfaneto.yams.models.services.ServiceType.Assembly;

import com.gersonfaneto.yams.models.entities.client.Client;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.orders.work.WorkOrder;
import com.gersonfaneto.yams.models.stock.Component;
import java.io.Serializable;
import java.util.List;

/**
 * Represents the Services that can be requested by the Clients of the Assistance and performed by
 * its Technicians, after being grouped in a Work Order.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see Client
 * @see Technician
 * @see WorkOrder
 */
public class Service implements Serializable {

  private String serviceID;
  private String workOrderID;
  private ServiceType serviceType;
  private String serviceDescription;
  private double clientRating;
  private double servicePrice;
  private boolean isComplete;
  private Component usedComponent;
  private int amountUsed;

  /**
   * Constructs a new <code>Service</code>.
   *
   * @param serviceType        The type of the <code>Service</code>.
   * @param serviceDescription The description of the <code>Service</code>.
   * @param usedComponent      The <code>Component</code> used on the <code>Service</code>.
   * @param amountUsed      The amount of the used <code>Component</code>.
   */
  public Service(
      ServiceType serviceType,
      String serviceDescription,
      Component usedComponent,
      int amountUsed
  ) {
    this.serviceType = serviceType;
    this.serviceDescription = serviceDescription;

    if (serviceType != Assembly) {
      this.servicePrice = serviceType.getTypeValue();
    } else {
      this.servicePrice = usedComponent.getComponentPrice();
    }

    this.amountUsed = amountUsed;

    this.isComplete = false;
    this.usedComponent = usedComponent;
  }

  /**
   * Compares an <code>Object</code> to the <code>Service</code>.
   *
   * @param otherObject The <code>Object</code> to be compared to.
   * @return <code>true</code> if the objects match, or <code>false</code> if they don't.
   */
  @Override
  public boolean equals(Object otherObject) {
    // Checking if the Object passed isn't the Service itself.
    if (this == otherObject) {
      return true;
    }

    // Checkin if the Object passed isn't null.
    if (otherObject == null) {
      return false;
    }

    // Checkin if the Object passed is from the Class Service and casting.
    if (!(otherObject instanceof Service otherService)) {
      return false;
    }

    // Comparing by IDs.
    return serviceID.equals(otherService.serviceID);
  }

  /**
   * Generate a <code>String</code> from the most important information of the
   * <code>Service</code>.
   *
   * @return Relevant information about the <code>Service</code>.
   */
  @Override
  public String toString() {
    return String.format("""
            ID: %s
            Type: %s
            Description: %s
            Price: R$ %.2f
            Status: %s
            """,
        serviceID,
        serviceType,
        serviceDescription,
        servicePrice,
        (isComplete) ? "Complete" : "Pending");
  }

  public String getServiceID() {
    return serviceID;
  }

  public void setServiceID(String serviceID) {
    this.serviceID = serviceID;
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

  public Component getUsedComponent() {
    return usedComponent;
  }

  public void setUsedComponents(Component usedComponent) {
    this.usedComponent = usedComponent;
  }

  public String getWorkOrderID() {
    return workOrderID;
  }

  public void setWorkOrderID(String workOrderID) {
    this.workOrderID = workOrderID;
  }

  public void setUsedComponent(Component usedComponent) {
    this.usedComponent = usedComponent;
  }

  public int getAmountUsed() {
    return amountUsed;
  }

  public void setAmountUsed(int amountUsed) {
    this.amountUsed = amountUsed;
  }
}
