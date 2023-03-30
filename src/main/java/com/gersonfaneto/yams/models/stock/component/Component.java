package com.gersonfaneto.techinfo.models.stock.component;

public class Component {
    private final ComponentType componentType;
    private final double componentCost;
    private String componentID;
    private String componentDescription;
    private double componentPrice;

    public Component(ComponentType componentType, String componentDescription, double componentCost) {
        this.componentID = "Undefined!";
        this.componentType = componentType;
        this.componentDescription = componentDescription;
        this.componentPrice = componentType.getTypeValue();
        this.componentCost = componentCost;
    }

    public Component(ComponentType componentType, String componentDescription, double componentPrice, double componentCost) {
        this.componentID = "Undefined!";
        this.componentType = componentType;
        this.componentDescription = componentDescription;
        this.componentPrice = componentPrice;
        this.componentCost = componentCost;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %s
                Type: %s
                Description: %s
                Price: R$ %.2f                
                Cost: R$ %.2f
                """, componentID, componentType.getTypeName(), componentDescription, componentPrice, componentCost);
    }

    @Override
    public boolean equals(Object objectToCompare) {
        if (objectToCompare instanceof Component otherComponent) {
            return otherComponent.componentID.equals(this.componentID);
        }

        return false;
    }

    public String getComponentID() {
        return componentID;
    }

    public void setComponentID(String componentID) {
        this.componentID = componentID;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public String getComponentDescription() {
        return componentDescription;
    }

    public void setComponentDescription(String componentDescription) {
        this.componentDescription = componentDescription;
    }

    public double getComponentPrice() {
        return componentPrice;
    }

    public void setComponentPrice(double componentPrice) {
        this.componentPrice = componentPrice;
    }

    public double getComponentCost() {
        return componentCost;
    }
}
