package com.gersonfaneto.techinfo.models;

import com.gersonfaneto.techinfo.models.billing.Invoice;
import com.gersonfaneto.techinfo.models.order.Order;
import com.gersonfaneto.techinfo.models.technician.Technician;
import com.gersonfaneto.techinfo.models.technician.TechnicianType;

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

    public boolean registerTechnician(String userName, String userPassword, TechnicianType technicianType) {
        for (Technician currentTechnician : registeredTechnicians) {
            if (currentTechnician.getUserName().equals(userName)) {
                return false;
            }
        }

        registeredTechnicians.add(new Technician(technicianType, userName, userPassword));

        return true;
    }

    public boolean loginTechnician(String userName, String userPassword) {
        for (Technician currentTechnician : registeredTechnicians) {
            if (currentTechnician.getUserName().equals(userName) && currentTechnician.getUserPassword().equals(userPassword)) {
                return true;
            }
        }

        return false;
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

    public static List<Technician> getRegisteredTechnicians() {
        return registeredTechnicians;
    }

    public static List<Client> getRegisteredClients() {
        return registeredClients;
    }

    public static List<Order> getOrderHistory() {
        return orderHistory;
    }

    public static List<Invoice> getInvoiceHistory() {
        return invoiceHistory;
    }

}
