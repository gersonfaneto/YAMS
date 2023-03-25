package com.gersonfaneto.techinfo.dao.order;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.order.Order;
import com.gersonfaneto.techinfo.models.order.OrderStatus;

import java.util.List;

public interface OrderDAO extends CRUD<Order> {
    public List<Order> findByClient(int clientID);

    public List<Order> findByTechnician(int technicianID);

    public List<Order> findByStatus(OrderStatus targetStatus);
}
