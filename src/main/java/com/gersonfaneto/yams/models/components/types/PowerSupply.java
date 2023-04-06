package com.gersonfaneto.yams.models.components.types;

import com.gersonfaneto.yams.models.components.Component;

public class PowerSupply extends Component {

  private static final String COMPONENT_TYPE = "PowerSupply";
  private static final double COMPONENT_PRICE = 30.00;

  public PowerSupply(String componentDescription, double componentCost) {
    super(COMPONENT_TYPE, componentDescription, componentCost, COMPONENT_PRICE);
  }
}
