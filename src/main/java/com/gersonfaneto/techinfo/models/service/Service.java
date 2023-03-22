package com.gersonfaneto.techinfo.models.service;

import com.gersonfaneto.techinfo.models.stock.Component;

import java.util.Date;

public class Service {
    private final ServiceType serviceType;
    private final Integer orderID;
    private Date openingDate;
    private Date closingDate;
    private Component usedComponent;
    private Double clientRating;
    private Double serviceCost;

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

    public Component addPartToService() {
        return null;
    }

    public Boolean removePartFromService() {
        return true;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public Double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(Double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public Double getClientRating() {
        return clientRating;
    }

    public void setClientRating(Double clientRating) {
        this.clientRating = clientRating;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Component getPart() {
        return usedComponent;
    }

    public void setPart(Component component) {
        this.usedComponent = component;
    }
}
