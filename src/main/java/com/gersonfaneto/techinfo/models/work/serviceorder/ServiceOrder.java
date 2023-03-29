package com.gersonfaneto.techinfo.models.work.serviceorder;

import com.gersonfaneto.techinfo.dao.DAO;
import com.gersonfaneto.techinfo.models.billing.Invoice;
import com.gersonfaneto.techinfo.models.stock.component.Component;
import com.gersonfaneto.techinfo.models.work.service.Service;
import com.gersonfaneto.techinfo.models.work.service.ServiceType;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.SplittableRandom;

import static com.gersonfaneto.techinfo.models.work.serviceorder.ServiceOrderStatus.*;

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

    public double calculatePrice() {
        List<Service> providedServices = DAO.fromServices().findByOrderID(this.serviceOrderID);
        double totalPrice = 0.0;

        for (Service currentService : providedServices) {
            totalPrice += currentService.getServicePrice();
        }

        return totalPrice;
    }

    public boolean generateInvoice() {
        if (this.serviceOrderStatus != Finished) {
            return false;
        }

        Invoice newInvoice = new Invoice(this.serviceOrderID, calculatePrice());
        DAO.fromInvoices().createOne(newInvoice);

        this.invoiceID = newInvoice.getInvoiceID();
        DAO.fromServiceOrders().updateInformation(this);

        return true;
    }

    public boolean addService(String technicianID, ServiceType serviceType, String serviceDescription, Component usedComponent) {
        if (this.serviceOrderStatus != Open || technicianID != this.technicianID) {
            return false;
        }

        Service newService = new Service(this.serviceOrderID, serviceType, serviceDescription, usedComponent);

        DAO.fromServices().createOne(newService);

        return true;
    }

    public boolean addService(String technicianID, String orderID, ServiceType serviceType, String serviceDescription) {
        if (this.serviceOrderStatus != Open || technicianID != this.technicianID) {
            return false;
        }

        Service newService = new Service(this.serviceOrderID, serviceType, serviceDescription);

        DAO.fromServices().createOne(newService);

        return true;
    }

    public boolean removeService(String technicianID, String serviceID) {
        if (this.serviceOrderStatus != Open || this.technicianID != technicianID) {
            return false;
        }

        DAO.fromServices().deleteByID(serviceID);

        return true;
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
