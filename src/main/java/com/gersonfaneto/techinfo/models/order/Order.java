package com.gersonfaneto.techinfo.models.order;

import com.gersonfaneto.techinfo.models.billing.Invoice;
import com.gersonfaneto.techinfo.models.service.Service;

import java.util.LinkedList;
import java.util.List;

public class Order {
    private static Integer referenceID = 0;
    private final Integer orderID;
    private final Integer invoiceID;
    private final Integer technicianID;
    private final Integer clientID;
    private List<Service> registeredServices;
    private OrderStatus orderStatus;
    private Double averageRating;

    public Order(Integer technicianID, Integer clientID) {
        this.orderID = ++Order.referenceID;
        this.invoiceID = -1;
        this.technicianID = -1;
        this.clientID = clientID;
        this.registeredServices = new LinkedList<>();
        this.orderStatus = OrderStatus.Open;
        this.averageRating = 0.0;
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
}
