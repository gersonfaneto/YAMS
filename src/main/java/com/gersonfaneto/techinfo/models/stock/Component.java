package com.gersonfaneto.techinfo.models.stock;

public class Component {
    private ComponentType componentType;
    private String partDescription;

    private Double partCost;

    public Component(ComponentType componentType, String partDescription, Double partCost) {
        this.componentType = componentType;
        this.partDescription = partDescription;
        this.partCost = partCost;
    }

    public Component(ComponentType componentType, Double partCost) {
        this.componentType = componentType;
        this.partDescription = "Not provided!";
        this.partCost = partCost;
    }

    public ComponentType getPartType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public Double getPartCost() {
        return partCost;
    }

    public void setPartCost(Double partCost) {
        this.partCost = partCost;
    }
}
