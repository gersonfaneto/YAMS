package com.gersonfaneto.techinfo.models.technician;

import com.gersonfaneto.techinfo.dao.DAO;
import com.gersonfaneto.techinfo.models.order.Order;
import com.gersonfaneto.techinfo.models.order.OrderStatus;
import com.gersonfaneto.techinfo.models.service.Service;
import com.gersonfaneto.techinfo.models.service.ServiceType;
import com.gersonfaneto.techinfo.models.stock.Component;
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
    private boolean hasOrderOpen;
    private Stock avaliableStock;

    public Technician(TechnicianType technicianType, String userName, String userPassword) {
        this.technicianType = technicianType;
        this.userName = userName;
        this.userPassword = userPassword;
        this.avaliableStock = Stock.retrieveStock();
    }

    public List<Order> retrieveOrderHistory() {
        return DAO.getOrders().findByTechnician(technicianID);
    }

    public List<PurchaseOrder> retrievePurchaseOrderHistory() {
        return DAO.getPurchaseOrders().findByTechnician(technicianID);
    }

    public boolean createOrder(ServiceType chosenService, Component usedComponent, double serviceCost, int clientID, boolean assignToSelf) {

        return true;
    }

    public boolean createOrder(ServiceType chosenService, double serviceCost, int clientID, boolean assignToSelf) {
        return true;
    }

    public boolean openOrder(int orderID) {
        if (hasOrderOpen) {
            return false;
        }

        Order targetOrder = DAO.getOrders().findByID(orderID);

        if (targetOrder.getOrderStatus() != OrderStatus.Created) {
            return false;
        }

        targetOrder.setOrderStatus(OrderStatus.Open);
        targetOrder.setTechnicianID(technicianID);

        DAO.getOrders().updateInformation(targetOrder);

        return true;
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
}
