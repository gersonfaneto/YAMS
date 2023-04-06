package com.gersonfaneto.yams.models.components.types;

import com.gersonfaneto.yams.models.components.Component;

public class Motherboard extends Component {

  private static final String COMPONENT_TYPE = "Motherboard";
  private static final double COMPONENT_PRICE = 100.00;

  public Motherboard(String componentDescription, double componentCost) {
    super(COMPONENT_TYPE, componentDescription, componentCost, COMPONENT_PRICE);
  }
}
