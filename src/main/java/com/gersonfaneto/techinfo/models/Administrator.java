package com.gersonfaneto.techinfo.models;

import com.gersonfaneto.techinfo.models.billing.Invoice;
import com.gersonfaneto.techinfo.models.order.Order;

import java.util.LinkedList;
import java.util.List;

public class Administrator {
    private Administrator singleInstance = null;
    private String userName;
    private String userPassword;
    private List<Technician> registeredTechnicians;
    private List<Client> registeredClients;
    private List<Order> orderHistory;
    private List<Invoice> invoiceHistory;

    private Administrator() {
    }

    public static Boolean loginTechnician() {
        return true;
    }

    public static Boolean logoffTechnician() {
        return true;
    }

    public Administrator retrieveAdministrator(String userName, String userPassword) {
        if (singleInstance == null) {
            singleInstance = new Administrator();
            singleInstance.userName = userName;
            singleInstance.userPassword = userPassword;
            registeredTechnicians = new LinkedList<>();
            registeredClients = new LinkedList<>();
            orderHistory = new LinkedList<>();
            invoiceHistory = new LinkedList<>();
        }
        return singleInstance;
    }

    public Boolean generateReport() {
        return true;
    }

    public Client registerClient() {
        return null;
    }

    public Technician registerTechnician() {
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public List<Technician> getRegisteredTechnicians() {
        return registeredTechnicians;
    }

    public List<Client> getRegisteredClients() {
        return registeredClients;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public List<Invoice> getInvoiceHistory() {
        return invoiceHistory;
    }
}
