package com.gersonfaneto.techinfo.models.order;

import com.gersonfaneto.techinfo.models.billing.Invoice;
import com.gersonfaneto.techinfo.models.service.Service;

import java.util.List;

public class Order {
    private static Integer referenceID = 0;
    private final Integer orderID;
    private final Integer invoiceID = -1;
    private final Integer technicianID;
    private final Integer clientID;
    private List<Service> registeredOrders;
    private OrderStatus orderStatus;
    private Double averageRating;

    public Order(Integer technicianID, Integer clientID) {
        this.orderStatus = OrderStatus.Open;
        this.orderID = ++Order.referenceID;
        this.technicianID = technicianID;
        this.clientID = clientID;
    }

    public static Integer getReferenceID() {
        return referenceID;
    }

    public static void setReferenceID(Integer referenceID) {
        Order.referenceID = referenceID;
    }

    public Invoice generateInvoice() {
        return null;
    }

    public Service addService() {
        return null;
    }

    public Boolean removeService() {
        return null;
    }
    
    public List<Service> getRegisteredOrders() {
        return registeredOrders;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public Integer getTechnicianID() {
        return technicianID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }


}
