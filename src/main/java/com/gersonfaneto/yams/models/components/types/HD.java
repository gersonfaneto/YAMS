package com.gersonfaneto.yams.models.components.types;

import com.gersonfaneto.yams.models.components.Component;

public class HD extends Component {

  private static final String COMPONENT_TYPE = "HD";
  private static final double COMPONENT_PRICE = 20.00;

  public HD(String componentDescription, double componentCost) {
    super(COMPONENT_TYPE, componentDescription, componentCost, COMPONENT_PRICE);
  }

  public HD() {
    super();
  }

  @Override
  public HD clone(String componentDescription, double componentCost) {
    return new HD(componentDescription, componentCost);
  }
}
