package com.gersonfaneto.yams.dao.stock;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.stock.Component;
import com.gersonfaneto.yams.models.stock.ComponentType;
import java.util.List;

public interface ComponentCRUD extends CRUD<Component> {

  List<Component> findByType(ComponentType componentType);
  Component findEquals(Object otherObject);
}
