package com.gersonfaneto.techinfo.models.entities;

import com.gersonfaneto.techinfo.models.entities.user.User;
import com.gersonfaneto.techinfo.models.entities.user.UserType;

public class Technician extends User {
    private final boolean hasOrderOpen;
    private String technicianName;

    public Technician(String userEmail, String userPassword, UserType userType, String technicianName) {
        super(userEmail, userPassword, userType);
        this.technicianName = technicianName;
        this.hasOrderOpen = false;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }
}
