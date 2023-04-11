package com.gersonfaneto.yams.dao.components;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.components.ComponentType;
import java.util.List;

public interface ComponentCRUD extends CRUD<Component> {

  List<Component> findByType(ComponentType componentType);
  Component findEquals(Object otherObject);
}
