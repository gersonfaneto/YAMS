package com.gersonfaneto.yams.dao.entities.user;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>UserCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> to store all the <code>User</code>s.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see UserCRUD
 */
public class UserMemoryDAO implements UserCRUD {

  private final Map<String, User> storedUsers;

  /**
   * Constructs a new <code>{@link UserMemoryDAO}</code>
   */
  public UserMemoryDAO() {
    this.storedUsers = new HashMap<>();
  }

  @Override
  public User createOne(User newTechnician) {
    String newID = Generators.randomID();

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
    return storedUsers.values()
        .stream()
        .toList();
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
    return storedUsers.values()
        .stream()
        .filter(x -> x.getUserEmail().equals(userEmail))
        .findFirst()
        .orElse(null);
  }
}
