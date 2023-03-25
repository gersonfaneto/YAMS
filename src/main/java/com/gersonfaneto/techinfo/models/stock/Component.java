package com.gersonfaneto.techinfo.models.stock;

public class Component {
    private ComponentType componentType;
    private int componentID;
    private String componentDescription;
    private double componentValue;

    public Component(ComponentType componentType, String componentDescription, double componentValue) {
        this.componentType = componentType;
        this.componentDescription = componentDescription;
        this.componentValue = componentValue;
    }

    public Component(ComponentType componentType, double componentValue) {
        this.componentType = componentType;
        this.componentDescription = "Not provided!";
        this.componentValue = componentValue;
    }

    public int getComponentID() {
        return componentID;
    }

    public void setComponentID(int componentID) {
        this.componentID = componentID;
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

    public double getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(double componentValue) {
        this.componentValue = componentValue;
    }
}
