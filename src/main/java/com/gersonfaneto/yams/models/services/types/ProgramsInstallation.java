package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.models.services.Service;

import java.util.List;

public class ProgramsInstallation extends Service {
    private static final double PRICE_PER_PROGRAM = 10.00;
    private List<String> chosenPrograms;

    public ProgramsInstallation(String serviceType, String serviceDescription, List<String> chosenPrograms) {
        super(serviceType, serviceDescription, chosenPrograms.size() * PRICE_PER_PROGRAM);
        this.chosenPrograms = chosenPrograms;
    }

    public boolean addProgram(String programName) {
        if (chosenPrograms.contains(programName)) {
            return false;
        }

        chosenPrograms.add(programName);

        return true;
    }

    public boolean removeProgram(String programName) {
        if (!chosenPrograms.contains(programName)) {
            return false;
        }

        chosenPrograms.remove(programName);

        return true;
    }

    public List<String> getChosenPrograms() {
        return chosenPrograms;
    }

    public void setChosenPrograms(List<String> chosenPrograms) {
        this.chosenPrograms = chosenPrograms;
    }
}
