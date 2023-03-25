package com.gersonfaneto.techinfo.models.service;

import com.gersonfaneto.techinfo.models.stock.Component;

import java.util.Calendar;

public class Service {
    private ServiceType serviceType;
    private int serviceID;
    private int orderID;
    private Calendar openingTime;
    private Calendar closingTime;
    private double serviceCost;
    private String serviceDescription;
    private Component usedComponent;
    private double clientRating;

    public Service(ServiceType serviceType, int orderID, Component usedComponent, String serviceDescription, double serviceCost) {
        this.serviceType = serviceType;
        this.orderID = orderID;
        this.openingTime = Calendar.getInstance();
        this.usedComponent = usedComponent;
        this.serviceDescription = serviceDescription;
        this.serviceCost = serviceCost;
    }

    public Service(ServiceType serviceType, int orderID, double serviceCost) {
        this.serviceType = serviceType;
        this.orderID = orderID;
        this.openingTime = Calendar.getInstance();
        this.serviceDescription = "Not provided!";
        this.serviceCost = serviceCost;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Calendar getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Calendar openingTime) {
        this.openingTime = openingTime;
    }

    public Calendar getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Calendar closingTime) {
        this.closingTime = closingTime;
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
}
