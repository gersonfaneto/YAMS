package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.models.services.Service;

public class Cleaning extends Service {

  private static final String SERVICE_TYPE = "Cleaning";
  private static final double BASE_PRICE = 70.00;

  public Cleaning(String workOrderID, String serviceDescription) {
    super(workOrderID, SERVICE_TYPE, serviceDescription, BASE_PRICE);
  }

  public Cleaning() {
    super();
  }

  @Override
  public Cleaning clone(String workOrderID, String serviceDescription) {
    return new Cleaning(workOrderID, serviceDescription);
  }
}
