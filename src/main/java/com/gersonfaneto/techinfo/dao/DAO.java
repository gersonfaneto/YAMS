package com.gersonfaneto.techinfo.dao;

import com.gersonfaneto.techinfo.dao.client.ClientDAO;
import com.gersonfaneto.techinfo.dao.client.ClientListCRUD;
import com.gersonfaneto.techinfo.dao.order.OrderDAO;
import com.gersonfaneto.techinfo.dao.order.OrderListCRUD;

public abstract class DAO {
    private static ClientDAO registeredClients;
    private static OrderDAO registeredOrders;

    public static ClientDAO getClients() {
        if (registeredClients == null) {
            registeredClients = new ClientListCRUD();
        }
        return registeredClients;
    }

    public static OrderDAO getOrders() {
        if (registeredOrders == null) {
            registeredOrders = new OrderListCRUD();
        }
        return registeredOrders;
    }
}
