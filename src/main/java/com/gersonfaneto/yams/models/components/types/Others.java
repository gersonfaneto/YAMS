package com.gersonfaneto.yams.models.components.types;

import com.gersonfaneto.yams.models.components.Component;

public class Others extends Component {

  public Others(String componentType, String componentDescription, double componentCost,
      double componentPrice) {
    super(componentType, componentDescription, componentCost, componentPrice);
  }

  public Others() {
    super();
  }

  @Override
  public Others clone(String componentDescription, double componentCost) {
    return new Others("Undefined", componentDescription, componentCost, 0.0);
  }
}
