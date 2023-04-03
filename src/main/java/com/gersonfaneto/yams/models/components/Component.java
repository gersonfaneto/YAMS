package com.gersonfaneto.yams.models.components;

public abstract class Component {
    private String componentID;
    private String componentType;
    private String componentDescription;
    private double componentCost;
    private double componentPrice;

    public Component(String componentType, String componentDescription, double componentCost, double componentPrice) {
        this.componentType = componentType;
        this.componentDescription = componentDescription;
        this.componentCost = componentCost;
        this.componentPrice = componentPrice;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Component otherComponent) {
            return otherComponent.componentID.equals(this.componentID);
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Type: %s
                Description: %s
                Cost: R$ %.2f
                Price: R$ %.2f
                """, componentID, componentType, componentDescription, componentCost, componentPrice);
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public double getComponentCost() {
        return componentCost;
    }

    public void setComponentCost(double componentCost) {
        this.componentCost = componentCost;
    }

    public double getComponentPrice() {
        return componentPrice;
    }

    public void setComponentPrice(double componentPrice) {
        this.componentPrice = componentPrice;
    }
}