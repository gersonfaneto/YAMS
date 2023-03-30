package com.gersonfaneto.yams.models.work.service;

import com.gersonfaneto.yams.models.stock.component.Component;

public class Service {
    private final String serviceOrderID;
    private final ServiceType serviceType;
    private final String serviceDescription;
    private String serviceID;
    private Component usedComponent;
    private double servicePrice;
    private double clientRating;

    public Service(String serviceOrderID, ServiceType serviceType, String serviceDescription, Component usedComponent) {
        this.serviceID = "Undefined!";
        this.serviceOrderID = serviceOrderID;
        this.serviceType = serviceType;
        this.serviceDescription = serviceDescription;
        this.usedComponent = usedComponent;
        this.servicePrice = usedComponent.getComponentPrice();
        this.clientRating = 0.0;
    }

    public Service(String serviceOrderID, ServiceType serviceType, String serviceDescription) {
        this.serviceID = "Undefined!";
        this.serviceOrderID = serviceOrderID;
        this.serviceType = serviceType;
        this.serviceDescription = serviceDescription;
        this.usedComponent = null;
        this.servicePrice = serviceType.getTypeValue();
        this.clientRating = 0.0;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Order: %s
                Type: %s
                Description: %s
                Price: %s
                Rating: %.2f
                """, serviceID, serviceOrderID, serviceType.getTypeName(), serviceDescription, servicePrice, clientRating);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof Service otherService) {
            return otherService.serviceID.equals(this.serviceID);
        }

        return false;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceOrderID() {
        return serviceOrderID;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public Component getUsedComponent() {
        return usedComponent;
    }

    public void setUsedComponent(Component usedComponent) {
        this.usedComponent = usedComponent;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public double getClientRating() {
        return clientRating;
    }

    public void setClientRating(double clientRating) {
        this.clientRating = clientRating;
    }
}
