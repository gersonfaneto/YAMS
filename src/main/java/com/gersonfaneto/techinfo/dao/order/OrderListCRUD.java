package com.gersonfaneto.techinfo.dao.order;

import com.gersonfaneto.techinfo.models.order.Order;
import com.gersonfaneto.techinfo.models.order.OrderStatus;

import java.util.Currency;
import java.util.LinkedList;
import java.util.List;

public class OrderListCRUD implements OrderDAO {
    private List<Order> orderList;
    private int referenceID;

    public OrderListCRUD() {
        this.orderList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public Order createOne(Order targetObject) {
        targetObject.setOrderID(++referenceID);
        orderList.add(targetObject);
        return targetObject;
    }

    @Override
    public Order findByID(int targetID) {
        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderID() == targetID) {
                return currentOrder;
            }
        }
        return null;
    }

    @Override
    public List<Order> findMany() {
        return orderList;
    }

    @Override
    public boolean updateInformation(Order targetObject) {
        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderID() == targetObject.getOrderID()) {
                int targetIndex = orderList.indexOf(currentOrder);
                orderList.set(targetIndex, targetObject);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderID() == targetID) {
                orderList.remove(currentOrder);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!orderList.isEmpty()){
            orderList.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<Order> findByClient(int clientID) {
        List<Order> foundOrders = new LinkedList<>();

        for (Order currentOrder : orderList) {
            if (currentOrder.getClientID() == clientID) {
                foundOrders.add(currentOrder);
            }
        }

        return foundOrders;
    }

    @Override
    public List<Order> findByTechnician(int technicianID) {
        List<Order> foundOrders = new LinkedList<>();

        for (Order currentOrder : orderList) {
            if (currentOrder.getClientID() == technicianID) {
                foundOrders.add(currentOrder);
            }
        }

        return foundOrders;
    }

    @Override
    public List<Order> findByStatus(OrderStatus targetStatus) {
        List<Order> foundOrders = new LinkedList<>();

        for (Order currentOrder : orderList) {
            if (currentOrder.getOrderStatus() == targetStatus) {
                foundOrders.add(currentOrder);
            }
        }

        return foundOrders;
    }
}