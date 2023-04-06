package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.models.services.Service;

public class Formatting extends Service {

  private static final double BASE_PRICE = 50.00;

  public Formatting(String workOrderID, String serviceType, String serviceDescription) {
    super(workOrderID, serviceType, serviceDescription, BASE_PRICE);
  }
}
