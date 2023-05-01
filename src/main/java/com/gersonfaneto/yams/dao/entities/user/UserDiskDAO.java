package com.gersonfaneto.yams.dao.entities.user;

import com.gersonfaneto.yams.dao.CRUD;
import com.gersonfaneto.yams.dao.ObjectIO;
import com.gersonfaneto.yams.dao.Persist;
import com.gersonfaneto.yams.models.entities.user.User;
import com.gersonfaneto.yams.utils.Generators;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementations for the <code>UserCRUD</code> and <code>CRUD</code> operations. Uses a <code>
 * HashMap</code> as a cache to store all the <code>User</code>s during the execution of the program
 * and loads or unloads the contents of it into a file using an <code>ObjectIO</code>.
 *
 * @author Gerson Ferreira dos Anjos Neto
 * @version 1.0.0
 * @see CRUD
 * @see UserCRUD
 * @see ObjectIO
 */
public class UserDiskDAO implements UserCRUD, Persist {

  private final Map<String, User> storedUsers;
  private final ObjectIO<User> userObjectIO;

  /**
   * Constructs a new <code>{@link UserDiskDAO}</code>.
   *
   * @param savePath The path of the file on which to save the data must be saved.
   */
  public UserDiskDAO(String savePath) {
    this.storedUsers = new HashMap<>();
    this.userObjectIO = new ObjectIO<>(savePath);
  }

  public boolean saveAll() {
    List<User> toSave = storedUsers.values()
        .stream()
        .toList();

    return userObjectIO.saveObjects(toSave);
  }

  public boolean loadAll() {
    List<User> loadedUsers = userObjectIO.loadObjects();

    if (loadedUsers == null) {
      return false;
    }

    for (User currentUser : loadedUsers) {
      storedUsers.put(currentUser.getUserID(), currentUser);
    }

    return true;
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
