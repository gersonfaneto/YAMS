package com.gersonfaneto.yams.dao.service;

import com.gersonfaneto.yams.models.work.service.Service;

import java.util.*;

public class ServiceListCRUD implements ServiceDAO {
    private final Map<String, Service> registeredServices;

    public ServiceListCRUD() {
        this.registeredServices = new HashMap<>();
    }

    @Override
    public Service createOne(Service newService) {
        String newID = UUID.randomUUID().toString();

        newService.setServiceID(newID);

        registeredServices.put(newID, newService);

        return newService;
    }

    @Override
    public Service findByID(String targetID) {
        return registeredServices.get(targetID);
    }

    @Override
    public List<Service> findMany() {
        List<Service> foundServices = new LinkedList<>();

        for (Service currentService : registeredServices.values()) {
            foundServices.add(currentService);
        }

        return foundServices;
    }

    @Override
    public boolean updateInformation(Service targetObject) {
        String serviceID = targetObject.getServiceID();

        if (registeredServices.containsKey(serviceID)) {
            registeredServices.put(serviceID, targetObject);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredServices.containsKey(targetID)) {
            registeredServices.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredServices.isEmpty()) {
            registeredServices.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<Service> findByOrderID(String targetID) {
        List<Service> foundServices = new LinkedList<>();

        for (Service currentService : registeredServices.values()) {
            if (currentService.getServiceOrderID().equals(targetID)) {
                foundServices.add(currentService);
            }
        }

        return foundServices;
    }
}
