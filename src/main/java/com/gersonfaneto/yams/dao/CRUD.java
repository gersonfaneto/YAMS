package com.gersonfaneto.yams.dao;

import java.util.List;

public interface CRUD<T> {
    T createOne(T targetObject);

    T findByID(String targetID);

    List<T> findMany();

    boolean updateInformation(T targetObject);

    boolean deleteByID(String targetID);

    boolean deleteMany();
}
