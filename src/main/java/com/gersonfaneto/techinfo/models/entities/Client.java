package com.gersonfaneto.techinfo.models.entities;

import com.gersonfaneto.techinfo.dao.DAO;
import com.gersonfaneto.techinfo.models.work.serviceorder.ServiceOrder;

import java.util.List;

public class Client {
    private String clientID;
    private String clientName;
    private String homeAddress;
    private String phoneNumber;

    public Client(String clientName, String homeAddress, String phoneNumber) {
        this.clientID = "Undefined!";
        this.clientName = clientName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
    }

    public List<ServiceOrder> retrieveServiceOrderHistory() {
        return DAO.fromServiceOrders().findByClient(this.clientID);
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Name: %s
                Address: %s
                Contact: %s
                """, clientID, clientName, homeAddress, phoneNumber);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof Client otherClient) {
            return otherClient.clientID.equals(this.clientID);
        }

        return false;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
