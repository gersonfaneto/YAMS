package com.gersonfaneto.yams.models.services.types;

import com.gersonfaneto.yams.models.components.Component;
import com.gersonfaneto.yams.models.services.Service;

import java.util.LinkedList;
import java.util.List;

public class Assembly extends Service {
    private List<Component> usedComponents;

    public Assembly(String serviceType, String serviceDescription, List<Component> usedComponents) {
        super(serviceType, serviceDescription, usedComponents.stream().map(Component::getComponentPrice).reduce(0.0, Double::sum));
        this.usedComponents = usedComponents;
    }

    // TODO: Add Implementations!
    public boolean addComponent(String componentID) {
        return true;
    }

    public boolean removeComponent(String componentID) {
        return true;
    }

    public List<Component> getUsedComponents() {
        return usedComponents;
    }

    public void setUsedComponents(List<Component> usedComponents) {
        this.usedComponents = usedComponents;
    }
}
