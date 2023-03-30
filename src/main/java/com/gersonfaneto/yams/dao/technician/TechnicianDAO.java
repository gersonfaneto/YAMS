package com.gersonfaneto.yams.dao.technician;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.Technician;

public interface TechnicianDAO extends CRUD<Technician> {
    public Technician findByEmail(String userEmail);
}
