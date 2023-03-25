package com.gersonfaneto.techinfo.models.order;

import com.gersonfaneto.techinfo.models.service.Service;

import java.util.LinkedList;
import java.util.List;

public class Order {
    private int orderID;
    private int invoiceID;
    private int clientID;
    private int technicianID;
    private final List<Service> registeredServices;
    private OrderStatus orderStatus;
    private double averageRating;

    public Order(int technicianID, int clientID) {
        this.technicianID = technicianID;
        this.clientID = clientID;
        this.registeredServices = new LinkedList<>();
        this.orderStatus = OrderStatus.WIP;
        this.averageRating = 0.0;
    }

    public Order(int clientID) {
        this.clientID = clientID;
        this.registeredServices = new LinkedList<>();
        this.orderStatus = OrderStatus.Open;
        this.averageRating = 0.0;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
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

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
