package com.gersonfaneto.techinfo.models;

import com.gersonfaneto.techinfo.models.order.Order;
import com.gersonfaneto.techinfo.models.stock.PurchaseOrder;
import com.gersonfaneto.techinfo.models.stock.Stock;

import java.util.LinkedList;
import java.util.List;

public class Technician {
    private static Integer referenceID = 0;
    private final Integer technicianID;
    private Boolean hasPrivileges;
    private String userName;
    private String userPassword;
    private List<Order> orderHistory;
    private List<PurchaseOrder> purchaseOrderHistory;
    private Stock avaliableStock;

    public Technician(Boolean hasPrivileges, String userName, String userPassword) {
        this.technicianID = ++Technician.referenceID;
        this.hasPrivileges = hasPrivileges;
        this.userName = userName;
        this.userPassword = userPassword;
        this.orderHistory = new LinkedList<>();
        this.purchaseOrderHistory = new LinkedList<>();
        this.avaliableStock = Stock.retrieveStock();
    }

    public Order createOrder() {
        return null;
    }

    public Order openOrder() {
        return null;
    }

    public Boolean closeOrder() {
        return true;
    }

    public Boolean finishOrder() {
        return true;
    }

    public PurchaseOrder buyPart() {
        return null;
    }

    public Integer getTechnicianID() {
        return technicianID;
    }

    public Boolean getADM() {
        return hasPrivileges;
    }

    public void setADM(Boolean ADM) {
        hasPrivileges = ADM;
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

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public List<PurchaseOrder> getPurchaseOrderHistory() {
        return purchaseOrderHistory;
    }
}
