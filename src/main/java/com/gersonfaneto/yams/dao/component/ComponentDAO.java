package com.gersonfaneto.yams.dao.component;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.stock.component.Component;
import com.gersonfaneto.yams.models.stock.component.ComponentType;

import java.util.List;

public interface
ComponentDAO extends CRUD<Component> {
    /**
     * Searches all the components on the DB that match the received type.
     *
     * @param targetType The type to be searched.
     * @return A list of the found components.
     */
    List<Component> findByType(ComponentType targetType);
}
