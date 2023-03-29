package com.gersonfaneto.techinfo.dao.technician;

import com.gersonfaneto.techinfo.models.entities.Technician;

import java.util.*;

public class TechnicianListCRUD implements TechnicianDAO {
    private final Map<String, Technician> registeredTechnicians;

    public TechnicianListCRUD() {
        this.registeredTechnicians = new HashMap<>();
    }

    @Override
    public Technician createOne(Technician newTechnician) {
        String newID = UUID.randomUUID().toString();

        newTechnician.setUserID(newID);

        registeredTechnicians.put(newID, newTechnician);

        return newTechnician;
    }

    @Override
    public Technician findByID(String targetID) {
        return registeredTechnicians.get(targetID);
    }

    @Override
    public List<Technician> findMany() {
        List<Technician> foundTechnicians = new LinkedList<>();

        for (Technician currentTechnician : registeredTechnicians.values()) {
            foundTechnicians.add(currentTechnician);
        }

        return foundTechnicians;
    }

    @Override
    public boolean updateInformation(Technician targetTechnician) {
        String technicianID = targetTechnician.getUserID();

        if (registeredTechnicians.containsKey(technicianID)) {
            registeredTechnicians.put(technicianID, targetTechnician);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteByID(String targetID) {
        if (registeredTechnicians.containsKey(targetID)) {
            registeredTechnicians.remove(targetID);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!registeredTechnicians.isEmpty()) {
            registeredTechnicians.clear();
            return true;
        }

        return false;
    }

    @Override
    public Technician findByEmail(String userEmail) {
        for (Technician currentTechnician : registeredTechnicians.values()) {
            if (currentTechnician.getUserEmail().equals(userEmail)) {
                return currentTechnician;
            }
        }

        return null;
    }
}
