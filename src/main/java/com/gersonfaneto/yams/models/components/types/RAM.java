package com.gersonfaneto.yams.models.components.types;

import com.gersonfaneto.yams.models.components.Component;

public class RAM extends Component {

  private static final String COMPONENT_TYPE = "RAM";
  private static final double COMPONENT_PRICE = 20.00;

  public RAM(String componentDescription, double componentCost) {
    super(COMPONENT_TYPE, componentDescription, componentCost, COMPONENT_PRICE);
  }
}
