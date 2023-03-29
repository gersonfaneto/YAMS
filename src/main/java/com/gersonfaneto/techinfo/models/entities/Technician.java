package com.gersonfaneto.techinfo.models.entities;

import com.gersonfaneto.techinfo.models.entities.user.User;
import com.gersonfaneto.techinfo.models.entities.user.UserType;

public class Technician extends User {
    private boolean hasOrderOpen;
    private String technicianName;

    public Technician(String userEmail, String userPassword, UserType userType, String technicianName) {
        super(userEmail, userPassword, userType);
        this.technicianName = technicianName;
        this.hasOrderOpen = false;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Name: %s
                Email: %s
                Password: %s
                User Type: %s
                """, super.getUserID(), technicianName, super.getUserEmail(), super.getUserPassword(), super.getUserType().getTypeName());
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof Technician) {
            Technician otherTechnician = (Technician) objectToCompare;
            return otherTechnician.getUserID().equals(this.getUserID());
        }

        return false;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public boolean isHasOrderOpen() {
        return hasOrderOpen;
    }

    public void setHasOrderOpen(boolean hasOrderOpen) {
        this.hasOrderOpen = hasOrderOpen;
    }
}
