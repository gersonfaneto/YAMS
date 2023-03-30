package com.gersonfaneto.yams.models.entities;

import com.gersonfaneto.yams.dao.DAO;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.models.entities.user.UserType;
import com.gersonfaneto.yams.models.stock.component.Component;
import com.gersonfaneto.yams.models.work.service.Service;
import com.gersonfaneto.yams.models.work.service.ServiceType;
import com.gersonfaneto.yams.models.work.serviceorder.ServiceOrder;

import java.util.List;

import static com.gersonfaneto.yams.models.entities.user.UserType.Administrator;
import static com.gersonfaneto.yams.models.work.serviceorder.ServiceOrderStatus.*;

public class Technician extends User {
    private boolean hasServiceOrderOpen;
    private String technicianName;

    public Technician(String userEmail, String userPassword, UserType userType, String technicianName) {
        super(userEmail, userPassword, userType);
        this.technicianName = technicianName;
        this.hasServiceOrderOpen = false;
    }

    // ? (#1) : Restring to "Administrator" only?
    public Client registerClient(String clientName, String homeAddress, String phoneNumber) {
        Client newClient = new Client(clientName, homeAddress, phoneNumber);

        return DAO.fromClients().createOne(newClient);
    }

    public boolean registerUser(String userEmail, String userPassword, UserType userType, String technicianName) {
        if (getUserType() != Administrator) {
            return false;
        }

        List<Technician> foundTechnicians = DAO.fromTechnicians().findMany();

        for (Technician foundTechnician : foundTechnicians) {
            if (foundTechnician.getUserEmail().equals(userEmail)) {
                return false;
            }
        }

        Technician newTechnician = new Technician(userEmail, userPassword, userType, technicianName);

        DAO.fromTechnicians().createOne(newTechnician);

        return true;
    }

    public static boolean loginUser(String userEmail, String userPassword) {
        List<Technician> foundTechnicians = DAO.fromTechnicians().findMany();

        for (Technician currentTechnician : foundTechnicians) {
            if (currentTechnician.getUserEmail().equals(userEmail) &&
                    currentTechnician.getUserPassword().equals(userPassword)) {
                return true;
            }
        }

        return false;
    }

    public static Technician retrieveUser(String userEmail, String userPassword) {
        if (loginUser(userEmail, userPassword)) {
            return DAO.fromTechnicians().findByEmail(userEmail);
        }

        return null;
    }

    public boolean createServiceOrder(String clientID) {
        if (hasServiceOrderOpen) {
            return false;
        }

        ServiceOrder newOrder = new ServiceOrder(clientID);

        DAO.fromServiceOrders().createOne(newOrder);

        return true;
    }

    public boolean addService(String serviceOrderID, ServiceType serviceType, String serviceDescription, Component usedComponent) {
        if (!hasServiceOrderOpen) {
            return false;
        }

        ServiceOrder targetServiceOrder = DAO.fromServiceOrders().findByID(serviceOrderID);

        if (targetServiceOrder.getServiceOrderStatus() != Open) {
            return false;
        }

        Service newService = new Service(serviceOrderID, serviceType, serviceDescription, usedComponent);
        DAO.fromServices().createOne(newService);

        return true;
    }

    public boolean addService(String serviceOrderID, ServiceType serviceType, String serviceDescription) {
        if (!hasServiceOrderOpen) {
            return false;
        }

        ServiceOrder targetServiceOrder = DAO.fromServiceOrders().findByID(serviceOrderID);

        if (targetServiceOrder.getServiceOrderStatus() != Open) {
            return false;
        }

        Service newService = new Service(serviceOrderID, serviceType, serviceDescription);
        DAO.fromServices().createOne(newService);

        return true;
    }

    public boolean removeService(String serviceID) {
        if (!hasServiceOrderOpen) {
            return false;
        }

        return DAO.fromServices().deleteByID(serviceID);
    }

    public boolean openOrder(String serviceOrderID) {
        if (hasServiceOrderOpen) {
            return false;
        }

        ServiceOrder targetServiceOrder = DAO.fromServiceOrders().findByID(serviceOrderID);

        if (targetServiceOrder.getServiceOrderStatus() != Created) {
            return false;
        }

        targetServiceOrder.setTechnicianID(this.getUserID());
        targetServiceOrder.setServiceOrderStatus(Open);
        DAO.fromServiceOrders().updateInformation(targetServiceOrder);

        this.hasServiceOrderOpen = true;

        return true;
    }

    public boolean cancelOrder(String serviceOrderID) {
        if (!hasServiceOrderOpen) {
            return false;
        }

        ServiceOrder targetServiceOrder = DAO.fromServiceOrders().findByID(serviceOrderID);

        if (targetServiceOrder.getServiceOrderStatus() != Open ||
                targetServiceOrder.getTechnicianID() != this.getUserID()) {
            return false;
        }

        targetServiceOrder.setServiceOrderStatus(Canceled);
        DAO.fromServiceOrders().updateInformation(targetServiceOrder);

        this.hasServiceOrderOpen = false;

        return true;
    }

    public boolean finishOrder(String serviceOrderID) {
        if (!hasServiceOrderOpen) {
            return false;
        }

        ServiceOrder targetServiceOrder = DAO.fromServiceOrders().findByID(serviceOrderID);

        if (targetServiceOrder.getServiceOrderStatus() != Open ||
                targetServiceOrder.getTechnicianID() != this.getUserID()) {
            return false;
        }

        targetServiceOrder.setServiceOrderStatus(Finished);
        DAO.fromServiceOrders().updateInformation(targetServiceOrder);

        this.hasServiceOrderOpen = false;

        return true;
    }

    public boolean generateInvoice(String serviceOrderID) {
        ServiceOrder targetServiceOrder = DAO.fromServiceOrders().findByID(serviceOrderID);

        if (targetServiceOrder.getServiceOrderStatus() != Finished) {
            return false;
        }

        targetServiceOrder.setServiceOrderStatus(PaymentPending);
        targetServiceOrder.generateInvoice();
        DAO.fromServiceOrders().updateInformation(targetServiceOrder);

        return true;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Name: %s
                Email: %s
                Password: %s
                User Type: %s
                """, super.getUserID(), technicianName, super.getUserEmail(), super.getUserPassword(), super.getUserType().getTypeName());
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof Technician) {
            Technician otherTechnician = (Technician) objectToCompare;
            return otherTechnician.getUserID().equals(this.getUserID());
        }

        return false;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public boolean hasServiceOrderOpen() {
        return hasServiceOrderOpen;
    }

    public void setHasOrderOpen(boolean hasServiceOrderOpen) {
        this.hasServiceOrderOpen = hasServiceOrderOpen;
    }
}
