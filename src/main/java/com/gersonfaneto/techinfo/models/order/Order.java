package com.gersonfaneto.techinfo.models.order;

import com.gersonfaneto.techinfo.models.service.Service;

import java.util.LinkedList;
import java.util.List;

public class Order {
    private static int referenceID = 0;
    private final int orderID;
    private final int invoiceID;
    private final int clientID;
    private int technicianID;
    private List<Service> registeredServices;
    private OrderStatus orderStatus;
    private Double averageRating;

    public Order(int technicianID, int clientID) {
        this.orderID = ++referenceID;
        this.invoiceID = -1;
        this.technicianID = technicianID;
        this.clientID = clientID;
        this.registeredServices = new LinkedList<>();
        this.orderStatus = OrderStatus.WIP;
        this.averageRating = 0.0;
    }

    public Order(int clientID) {
        this.orderID = ++referenceID;
        this.invoiceID = -1;
        this.technicianID = -1;
        this.clientID = clientID;
        this.registeredServices = new LinkedList<>();
        this.orderStatus = OrderStatus.Open;
        this.averageRating = 0.0;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public int getClientID() {
        return clientID;
    }

    public int getTechnicianID() {
        return technicianID;
    }

    public boolean setTechnicianID(int technicianID) {
        if (this.technicianID == -1) {
            this.technicianID = technicianID;
            return true;
        }
        return false;
    }

    public List<Service> getRegisteredServices() {
        return registeredServices;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
