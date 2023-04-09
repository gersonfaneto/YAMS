package com.gersonfaneto.yams.dao.entities.technician;

import com.gersonfaneto.yams.models.entities.technician.Technician;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TechnicianMemoryDAO implements TechnicianCRUD {

  private final Map<String, Technician> storedTechnicians;

  public TechnicianMemoryDAO() {
    this.storedTechnicians = new HashMap<>();
  }

  @Override
  public Technician createOne(Technician newTechnician) {
    String newID = UUID.randomUUID().toString();

    newTechnician.setUserID(newID);
    storedTechnicians.put(newID, newTechnician);

    return newTechnician;
  }

  @Override
  public Technician findByID(String targetID) {
    return storedTechnicians.get(targetID);
  }

  @Override
  public List<Technician> findMany() {
    return storedTechnicians.values().stream().toList();
  }

  @Override
  public boolean updateInformation(Technician updatedTechnician) {
    String technicianID = updatedTechnician.getUserID();

    if (storedTechnicians.containsKey(technicianID)) {
      storedTechnicians.put(technicianID, updatedTechnician);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteByID(String targetID) {
    if (storedTechnicians.containsKey(targetID)) {
      storedTechnicians.remove(targetID);
      return true;
    }

    return false;
  }

  @Override
  public boolean deleteMany() {
    if (!storedTechnicians.isEmpty()) {
      storedTechnicians.clear();
      return true;
    }

    return false;
  }

  @Override
  public Technician findByEmail(String userEmail) {
    return storedTechnicians.values().stream()
        .filter(x -> x.getUserEmail().equals(userEmail))
        .findFirst()
        .orElse(null);
  }
}
