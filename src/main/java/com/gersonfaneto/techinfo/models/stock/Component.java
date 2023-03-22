package com.gersonfaneto.techinfo.models.stock;

public class Component {
    private ComponentType componentType;
    private String componentDescription;

    private Double componentCost;

    public Component(ComponentType componentType, String componentDescription, Double componentCost) {
        this.componentType = componentType;
        this.componentDescription = componentDescription;
        this.componentCost = componentCost;
    }

    public Component(ComponentType componentType, Double componentCost) {
        this.componentType = componentType;
        this.componentDescription = "Not provided!";
        this.componentCost = componentCost;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public Double getComponentCost() {
        return componentCost;
    }

    public void setComponentCost(Double componentCost) {
        this.componentCost = componentCost;
    }
}
