package com.gersonfaneto.techinfo.dao;

import java.util.List;

public interface CRUD<T> {
    public T createOne(T targetObject);

    public T findByID(int targetID);

    public List<T> findMany();

    public boolean updateInformation(T targetObject);

    public boolean deleteByID(int targetID);

    public boolean deleteMany();
}
