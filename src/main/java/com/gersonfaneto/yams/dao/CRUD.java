package com.gersonfaneto.yams.dao;

import java.util.List;

public interface CRUD<T> {

  T createOne(T newObject);

  T findByID(String targetID);

  List<T> findMany();

  boolean updateInformation(T updatedObject);

  boolean deleteByID(String targetID);

  boolean deleteMany();
}
