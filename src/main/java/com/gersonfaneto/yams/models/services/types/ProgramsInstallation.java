package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.models.services.Service;
import java.util.LinkedList;
import java.util.List;

public class ProgramsInstallation extends Service {

  private static final String SERVICE_TYPE = "Programs Installation";
  private static final double PRICE_PER_PROGRAM = 10.00;
  private final List<String> chosenPrograms;

  public ProgramsInstallation(String workOrderID, String serviceDescription) {
    super(workOrderID, SERVICE_TYPE, serviceDescription, 0.0);
    this.chosenPrograms = new LinkedList<>();
  }

  public ProgramsInstallation() {
    super();
    this.chosenPrograms = new LinkedList<>();
  }

  @Override
  public ProgramsInstallation clone(String workOrderID, String serviceDescription) {
    return new ProgramsInstallation(workOrderID, serviceDescription);
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
}
