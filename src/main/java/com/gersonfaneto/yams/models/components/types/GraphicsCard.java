package com.gersonfaneto.yams.models.components.types;

import com.gersonfaneto.yams.models.components.Component;

public class GraphicsCard extends Component {

  private static final String COMPONENT_TYPE = "Graphics Card";
  private static final double COMPONENT_PRICE = 100.00;

  public GraphicsCard(String componentDescription, double componentCost) {
    super(COMPONENT_TYPE, componentDescription, componentCost, COMPONENT_PRICE);
  }
}
