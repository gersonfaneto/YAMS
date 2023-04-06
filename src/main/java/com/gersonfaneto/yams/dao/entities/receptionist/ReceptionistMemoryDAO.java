package com.gersonfaneto.yams.dao.entities.receptionist;

import com.gersonfaneto.yams.models.entities.Receptionist;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ReceptionistMemoryDAO implements ReceptionistCRUD {

  private final Map<String, Receptionist> storedReceptionists;

  public ReceptionistMemoryDAO() {
    this.storedReceptionists = new HashMap<>();
  }

  @Override
  public Receptionist createOne(Receptionist newReceptionist) {
    String newID = UUID.randomUUID().toString();

    newReceptionist.setUserID(newID);

    storedReceptionists.put(newID, newReceptionist);

    return newReceptionist;
  }

  @Override
  public Receptionist findByID(String targetID) {
    return storedReceptionists.get(targetID);
  }

  @Override
  public List<Receptionist> findMany() {
    return storedReceptionists.values().stream().toList();
  }

  @Override
  public boolean updateInformation(Receptionist updatedReceptionist) {
    String receptionistID = updatedReceptionist.getUserID();

    if (storedReceptionists.containsKey(receptionistID)) {
      storedReceptionists.put(receptionistID, updatedReceptionist);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedReceptionists.containsKey(targetID)) {
      storedReceptionists.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedReceptionists.isEmpty()) {
      storedReceptionists.clear();
      return true;
    }

    return false;
  }
}
