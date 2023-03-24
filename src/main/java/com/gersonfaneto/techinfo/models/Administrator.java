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

    public static Administrator registerAdministrator(String userName, String userPassword) {
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

    public static boolean loginAdministrator(String userName, String userPassword) {
        if (singleInstance != null) {
            return singleInstance.userName.equals(userName) && singleInstance.userPassword.equals(userPassword);
        }

        return false;
    }

    public Technician registerTechnician() {
        return null;
    }

    public boolean loginTechnician(String userName, String userPassword) {
        return true;
    }

    public boolean logoffTechnician(String userName, String userPassword) {
        return true;
    }

    public boolean generateReport() {
        return true;
    }

    public Client registerClient() {
        return null;
    }
}
