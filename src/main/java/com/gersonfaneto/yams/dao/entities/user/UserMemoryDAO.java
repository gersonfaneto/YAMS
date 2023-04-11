package com.gersonfaneto.yams.dao.entities.user;

import com.gersonfaneto.yams.models.entities.user.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserMemoryDAO implements UserCRUD {

  private final Map<String, User> storedUsers;

  public UserMemoryDAO() {
    this.storedUsers = new HashMap<>();
  }

  @Override
  public User createOne(User newTechnician) {
    String newID = UUID.randomUUID().toString();

    newTechnician.setUserID(newID);
    storedUsers.put(newID, newTechnician);

    return newTechnician;
  }

  @Override
  public User findByID(String targetID) {
    return storedUsers.get(targetID);
  }

  @Override
  public List<User> findMany() {
    return storedUsers.values().stream().toList();
  }

  @Override
  public boolean updateInformation(User updatedTechnician) {
    String technicianID = updatedTechnician.getUserID();

    if (storedUsers.containsKey(technicianID)) {
      storedUsers.put(technicianID, updatedTechnician);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedUsers.containsKey(targetID)) {
      storedUsers.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedUsers.isEmpty()) {
      storedUsers.clear();
      return true;
    }

    return false;
  }

  @Override
  public User findByEmail(String userEmail) {
    return storedUsers.values().stream()
        .filter(x -> x.getUserEmail().equals(userEmail))
        .findFirst()
        .orElse(null);
  }
}
