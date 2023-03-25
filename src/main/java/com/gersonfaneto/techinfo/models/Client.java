package com.gersonfaneto.techinfo.models;

import com.gersonfaneto.techinfo.models.order.Order;

import java.util.LinkedList;
import java.util.List;

public class Client {
    private int clientID;
    private String clientName;
    private String homeAddress;
    private String phoneNumber;
    private List<Order> orderHistory;

    public Client(String clientName, String homeAddress, String phoneNumber) {
        this.clientName = clientName;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.orderHistory = new LinkedList<>();
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
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

    public List<Order> getOrderHistory() {
        return orderHistory;
    }
}
