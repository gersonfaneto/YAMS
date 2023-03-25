package com.gersonfaneto.techinfo.dao.service;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.service.Service;

import java.util.List;

public interface ServiceDAO extends CRUD<Service> {
    public List<Service> findByOrderID(int targetID);
}
