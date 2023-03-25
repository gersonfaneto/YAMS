package com.gersonfaneto.techinfo.dao.stock.component;

import com.gersonfaneto.techinfo.dao.CRUD;
import com.gersonfaneto.techinfo.models.stock.Component;
import com.gersonfaneto.techinfo.models.stock.ComponentType;

import java.util.List;

public interface ComponentDAO extends CRUD<Component> {
    public List<Component> findByType(ComponentType targetType);
}
