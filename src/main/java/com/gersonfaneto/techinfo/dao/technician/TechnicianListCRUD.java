package com.gersonfaneto.techinfo.dao.technician;

import com.gersonfaneto.techinfo.models.technician.Technician;

import java.util.LinkedList;
import java.util.List;

public class TechnicianListCRUD implements TechnicianDAO {
    private List<Technician> technicianList;
    private int referenceID;

    public TechnicianListCRUD() {
        this.technicianList = new LinkedList<>();
        this.referenceID = 0;
    }

    @Override
    public Technician createOne(Technician targetObject) {
        targetObject.setTechnicianID(++referenceID);
        technicianList.add(targetObject);
        return targetObject;
    }

    @Override
    public Technician findByID(int targetID) {
        for (Technician currentTechnician : technicianList) {
            if (currentTechnician.getTechnicianID() == targetID) {
                return currentTechnician;
            }
        }
        return null;
    }

    @Override
    public List<Technician> findMany() {
        return technicianList;
    }

    @Override
    public boolean updateInformation(Technician targetObject) {
        for (Technician currentTechnician : technicianList) {
            if (currentTechnician.getTechnicianID() == targetObject.getTechnicianID()) {
                int targetIndex = technicianList.indexOf(currentTechnician);
                technicianList.set(targetIndex, targetObject);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteByID(int targetID) {
        for (Technician currentTechnician : technicianList) {
            if (currentTechnician.getTechnicianID() == targetID) {
                technicianList.remove(currentTechnician);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMany() {
        if (!technicianList.isEmpty()) {
            technicianList.clear();
            return true;
        }
        return false;
    }
}
