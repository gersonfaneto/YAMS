package com.gersonfaneto.techinfo.models.service;

import com.gersonfaneto.techinfo.models.stock.Component;

import java.util.Date;

public class Service {
    private static int referenceID = 0;
    private final ServiceType serviceType;
    private final int orderID;
    private final Date openingDate;
    private Date closingDate;
    private double serviceCost;
    private Component usedComponent;
    private double clientRating;

    public Service(ServiceType serviceType, Date openingDate, Component usedComponent, double serviceCost) {
        this.serviceType = serviceType;
        this.orderID = ++referenceID;
        this.openingDate = openingDate;
        this.closingDate = null;
        this.usedComponent = usedComponent;
        this.clientRating = 0.0;
        this.serviceCost = serviceCost;
    }

    public Service(ServiceType serviceType, Date openingDate, double serviceCost) {
        this.serviceType = serviceType;
        this.orderID = ++referenceID;
        this.openingDate = openingDate;
        this.closingDate = null;
        this.usedComponent = null;
        this.clientRating = 0.0;
        this.serviceCost = serviceCost;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Component getUsedComponent() {
        return usedComponent;
    }

    public void setUsedComponent(Component usedComponent) {
        this.usedComponent = usedComponent;
    }

    public double getClientRating() {
        return clientRating;
    }

    public void setClientRating(double clientRating) {
        this.clientRating = clientRating;
    }
}
