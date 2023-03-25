package com.gersonfaneto.techinfo.dao.service;

import com.gersonfaneto.techinfo.models.service.Service;

import java.util.LinkedList;
import java.util.List;

public class ServiceListCRUD implements ServiceDAO {
    private List<Service> serviceList;
    private int referenceID;

    public ServiceListCRUD() {
        this.serviceList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public Service createOne(Service targetObject) {
        targetObject.setServiceID(++referenceID);
        serviceList.add(targetObject);
        return targetObject;
    }

    @Override
    public Service findByID(int targetID) {
        for (Service currentService : serviceList) {
            if (currentService.getServiceID() == targetID) {
                return currentService;
            }
        }

        return null;
    }

    @Override
    public List<Service> findMany() {
        return serviceList;
    }

    @Override
    public boolean updateInformation(Service targetObject) {
        for (Service currentService : serviceList) {
            if (currentService.getServiceID() == targetObject.getServiceID()) {
                int targetIndex = serviceList.indexOf(currentService);
                serviceList.set(targetIndex, targetObject);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (Service currentService : serviceList) {
            if (currentService.getServiceID() == targetID) {
                serviceList.remove(currentService);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!serviceList.isEmpty()) {
            serviceList.clear();
            return true;
        }
        return false;
    }

    @Override
    public List<Service> findByOrderID(int targetID) {
        List<Service> foundServices = new LinkedList<>();

        for (Service currentService : serviceList) {
            if (currentService.getOrderID() == targetID) {
                foundServices.add(currentService);
            }
        }

        return foundServices;
    }
}
