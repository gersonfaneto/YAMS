package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.models.services.Service;

public class Formatting extends Service {
  private static final String SERVICE_TYPE = "Formatting";
  private static final double BASE_PRICE = 50.00;
  private String chosenOS;

  public Formatting(String workOrderID, String serviceDescription) {
    super(workOrderID, SERVICE_TYPE, serviceDescription, BASE_PRICE);
  }

  public Formatting() {
    super();
  }

  @Override
  public Formatting clone(String workOrderID, String serviceDescription) {
    return new Formatting(workOrderID, serviceDescription);
  }

  public String getChosenOS() {
    return chosenOS;
  }

  public void setChosenOS(String chosenOS) {
    this.chosenOS = chosenOS;
  }
}
