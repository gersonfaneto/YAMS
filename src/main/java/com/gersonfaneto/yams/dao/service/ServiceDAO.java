package com.gersonfaneto.yams.dao.service;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.work.service.Service;

import java.util.List;

public interface ServiceDAO extends CRUD<Service> {
    List<Service> findByOrderID(String targetID);
}
