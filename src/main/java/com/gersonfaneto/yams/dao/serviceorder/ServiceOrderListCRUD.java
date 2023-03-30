package com.gersonfaneto.techinfo.dao.serviceorder;

import com.gersonfaneto.techinfo.models.work.serviceorder.ServiceOrder;
import com.gersonfaneto.techinfo.models.work.serviceorder.ServiceOrderStatus;

import java.util.*;

public class ServiceOrderListCRUD implements ServiceOrderDAO {
    private final Map<String, ServiceOrder> registeredServiceOrders;
    private final Queue<ServiceOrder> serviceOrdersQueue;

    public ServiceOrderListCRUD() {
        this.registeredServiceOrders = new HashMap<>();
        this.serviceOrdersQueue = new LinkedList<>();
    }

    @Override
    public ServiceOrder createOne(ServiceOrder newServiceOrder) {
        String newID = UUID.randomUUID().toString();

        newServiceOrder.setServiceOrderID(newID);

        registeredServiceOrders.put(newID, newServiceOrder);
        serviceOrdersQueue.add(newServiceOrder);

        return newServiceOrder;
    }

    @Override
    public ServiceOrder findByID(String targetID) {
        return registeredServiceOrders.get(targetID);
    }

    @Override
    public List<ServiceOrder> findMany() {
        List<ServiceOrder> foundServiceOrders = new LinkedList<>();

        for (ServiceOrder currentServiceOrder : registeredServiceOrders.values()) {
            foundServiceOrders.add(currentServiceOrder);
        }

        return foundServiceOrders;
    }

    @Override
    public boolean updateInformation(ServiceOrder targetServiceOrder) {
        String serviceOrderID = targetServiceOrder.getServiceOrderID();

        if (registeredServiceOrders.containsKey(serviceOrderID)) {
            registeredServiceOrders.put(serviceOrderID, targetServiceOrder);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredServiceOrders.containsKey(targetID)) {
            registeredServiceOrders.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredServiceOrders.isEmpty()) {
            registeredServiceOrders.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<ServiceOrder> findByClient(String clientID) {
        List<ServiceOrder> foundServiceOrders = new LinkedList<>();

        for (ServiceOrder currentServiceOrder : registeredServiceOrders.values()) {
            if (currentServiceOrder.getClientID().equals(clientID)) {
                foundServiceOrders.add(currentServiceOrder);
            }
        }

        return foundServiceOrders;
    }

    @Override
    public List<ServiceOrder> findByTechnician(String technicianID) {
        List<ServiceOrder> foundServiceOrders = new LinkedList<>();

        for (ServiceOrder currentServiceOrder : registeredServiceOrders.values()) {
            if (currentServiceOrder.getClientID().equals(technicianID)) {
                foundServiceOrders.add(currentServiceOrder);
            }
        }

        return foundServiceOrders;
    }

    @Override
    public List<ServiceOrder> findByStatus(ServiceOrderStatus targetStatus) {
        List<ServiceOrder> foundServiceOrders = new LinkedList<>();

        for (ServiceOrder currentServiceOrder : registeredServiceOrders.values()) {
            if (currentServiceOrder.getServiceOrderStatus() == targetStatus) {
                foundServiceOrders.add(currentServiceOrder);
            }
        }

        return foundServiceOrders;
    }

    @Override
    public Queue<ServiceOrder> findAllNext() {
        return serviceOrdersQueue;
    }

    @Override
    public ServiceOrder deleteNext() {
        return serviceOrdersQueue.remove();
    }

}