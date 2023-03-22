package com.gersonfaneto.techinfo.models.service;

import com.gersonfaneto.techinfo.models.stock.Component;

import java.util.Date;

public class Service {
    private final ServiceType serviceType;
    private final Integer orderID;
    private Date openingDate;
    private Date closingDate;
    private Double serviceCost;
    private Component usedComponent;
    private Double clientRating;

    public Service(ServiceType serviceType, Date openingDate, Integer orderID, Component usedComponent) {
        this.serviceType = serviceType;
        this.orderID = orderID;
        this.openingDate = openingDate;
        this.closingDate = null;
        this.usedComponent = usedComponent;
        this.clientRating = 0.0;
        this.serviceCost = 0.0;
    }

    public Service(ServiceType serviceType, Integer orderID, Date openingDate) {
        this.serviceType = serviceType;
        this.orderID = orderID;
        this.openingDate = openingDate;
        this.closingDate = null;
        this.usedComponent = null;
        this.clientRating = 0.0;
        this.serviceCost = 0.0;
    }

    public Component addComponent() {
        return null;
    }

    public Boolean removeComponent() {
        return true;
    }
}
