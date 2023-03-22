package com.gersonfaneto.techinfo.models;

import com.gersonfaneto.techinfo.models.billing.Invoice;
import com.gersonfaneto.techinfo.models.order.Order;

import java.util.LinkedList;
import java.util.List;

public class Administrator {
    private static Administrator singleInstance;
    private String userName;
    private String userPassword;
    private static List<Technician> registeredTechnicians;
    private static List<Client> registeredClients;
    private static List<Order> orderHistory;
    private static List<Invoice> invoiceHistory;

    private Administrator() {
    }

    public static Boolean loginTechnician() {
        return true;
    }

    public static Boolean logoffTechnician() {
        return true;
    }

    public static Administrator retrieveAdministrator(String userName, String userPassword) {
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
}
