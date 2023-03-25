package com.gersonfaneto.techinfo.models.order;

import com.gersonfaneto.techinfo.dao.DAO;
import com.gersonfaneto.techinfo.models.service.Service;
import java.util.List;

public class Order {
    private int orderID;
    private int invoiceID;
    private int clientID;
    private int technicianID;
    private OrderStatus orderStatus;
    private double averageRating;

    public Order(int technicianID, int clientID) {
        this.technicianID = technicianID;
        this.clientID = clientID;
        this.orderStatus = OrderStatus.WIP;
        this.averageRating = 0.0;
    }

    public Order(int clientID) {
        this.clientID = clientID;
        this.orderStatus = OrderStatus.Open;
        this.averageRating = 0.0;
    }

    public List<Service> retrieveRelatedServices() {
        return DAO.getServices().findByOrderID(orderID);
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

    public void setTechnicianID(int technicianID) {
        this.technicianID = technicianID;
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
