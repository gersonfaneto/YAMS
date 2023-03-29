package com.gersonfaneto.techinfo.models.work.serviceorder;

import com.gersonfaneto.techinfo.models.work.service.Service;

import java.util.Calendar;
import java.util.SplittableRandom;

import static com.gersonfaneto.techinfo.models.work.serviceorder.ServiceOrderStatus.Created;

public class ServiceOrder {
    private final String clientID;
    private final Calendar openedAt;
    private String serviceOrderID;
    private String technicianID;
    private String invoiceID;
    private Calendar closedAt;
    private ServiceOrderStatus serviceOrderStatus;
    private double averageRating;

    public ServiceOrder(String clientID) {
        this.serviceOrderID = "Undefined!";
        this.clientID = clientID;
        this.technicianID = "Undefined!";
        this.invoiceID = "Undefined!";
        this.openedAt = Calendar.getInstance();
        this.closedAt = null;
        this.serviceOrderStatus = Created;
        this.averageRating = 0.0;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Client: %s
                Opened At: %s
                Status: %s
                """, serviceOrderID, clientID, openedAt.toString(), serviceOrderStatus.getTypeName());
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof ServiceOrder) {
            ServiceOrder otherServiceOrder = (ServiceOrder) objectToCompare;
            return otherServiceOrder.serviceOrderID.equals(this.serviceOrderID);
        }

        return false;
    }

    public String getServiceOrderID() {
        return serviceOrderID;
    }

    public void setServiceOrderID(String serviceOrderID) {
        this.serviceOrderID = serviceOrderID;
    }

    public String getClientID() {
        return clientID;
    }

    public String getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(String technicianID) {
        this.technicianID = technicianID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Calendar getOpenedAt() {
        return openedAt;
    }

    public Calendar getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Calendar closedAt) {
        this.closedAt = closedAt;
    }

    public ServiceOrderStatus getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setServiceOrderStatus(ServiceOrderStatus serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
}
