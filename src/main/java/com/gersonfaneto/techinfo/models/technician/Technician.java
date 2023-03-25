package com.gersonfaneto.techinfo.models.technician;

import com.gersonfaneto.techinfo.models.order.Order;
import com.gersonfaneto.techinfo.models.stock.PurchaseOrder;
import com.gersonfaneto.techinfo.models.stock.Stock;

import java.util.LinkedList;
import java.util.List;

public class Technician {
    private int technicianID;
    private TechnicianType technicianType;
    private String technicianName;
    private String userName;
    private String userPassword;
    private List<Order> orderHistory;
    private List<PurchaseOrder> purchaseOrderHistory;
    private Stock avaliableStock;

    public Technician(TechnicianType technicianType, String userName, String userPassword) {
        this.technicianType = technicianType;
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

    public boolean closeOrder() {
        return true;
    }

    public boolean finishOrder() {
        return true;
    }

    public PurchaseOrder buyPart() {
        return null;
    }

    public int getTechnicianID() {
        return technicianID;
    }

    public void setTechnicianID(int technicianID) {
        this.technicianID = technicianID;
    }

    public TechnicianType getTechnicianType() {
        return technicianType;
    }

    public void setTechnicianType(TechnicianType technicianType) {
        this.technicianType = technicianType;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
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
