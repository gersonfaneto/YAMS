package com.gersonfaneto.yams.models.services;

public abstract class Service {
    private String serviceID;
    private String serviceType;
    private String serviceDescription;
    private double servicePrice;

    public Service(String serviceType, String serviceDescription, double servicePrice) {
        this.serviceType = serviceType;
        this.serviceDescription = serviceDescription;
        this.servicePrice = servicePrice;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Service otherService) {
            return otherService.serviceID.equals(this.serviceID);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Type: %s
                Description: %s
                Price: R$ %.2f
                """, serviceID, serviceType, serviceDescription, servicePrice);
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
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

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
}
