package com.gersonfaneto.yams.factories.types;

import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.components.types.GraphicsCard;
import com.gersonfaneto.yams.models.components.types.HD;
import com.gersonfaneto.yams.models.components.types.Motherboard;
import com.gersonfaneto.yams.models.components.types.Others;
import com.gersonfaneto.yams.models.components.types.PowerSupply;
import com.gersonfaneto.yams.models.components.types.RAM;
import com.gersonfaneto.yams.models.components.types.SSD;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

public class ComponentFactory {
  private final Map<String, Component> componentPrototypes;

  public ComponentFactory() {
    this.componentPrototypes = new HashMap<>();

    componentPrototypes.put("Graphics Card", new GraphicsCard());
    componentPrototypes.put("HD", new HD());
    componentPrototypes.put("Motherboard", new Motherboard());
    componentPrototypes.put("Power Supply", new PowerSupply());
    componentPrototypes.put("RAM", new RAM());
    componentPrototypes.put("SSD", new SSD());
    componentPrototypes.put("Others", new Others());
  }

  public Component generateComponent(String componentType, String componentDescription, double componentCost) {
    if (!componentPrototypes.containsKey(componentType)) {
      throw new InvalidParameterException("Component type not known!");
    }

    return componentPrototypes.get(componentType).clone(componentDescription, componentCost);
  }
}
