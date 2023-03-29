package com.gersonfaneto.techinfo.dao.service;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.work.service.Service;

import java.util.List;

public interface ServiceDAO extends CRUD<Service> {
    List<Service> findByOrderID(String targetID);
}
