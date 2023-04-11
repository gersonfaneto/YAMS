package com.gersonfaneto.yams.dao.entities.user;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.technician.Technician;
import com.gersonfaneto.yams.models.entities.user.User;

public interface UserCRUD extends CRUD<User> {
  public User findByEmail(String userEmail);
}
