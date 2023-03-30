package com.gersonfaneto.techinfo.models.stock.component;

public enum ComponentType {
    RAM("RAM", 20.00),
    Motherboard("Motherboard", 100.00),
    PowerSupply("Power Supply", 30.00),
    GraphicsCard("Graphics Card", 100.00),
    HD("HD", 30.00),
    SSD("SSD", 30.00),
    Other("Other", 0.00);

    private final String typeName;
    private final double typeValue;

    ComponentType(String typeName, double typeValue) {
        this.typeName = typeName;
        this.typeValue = typeValue;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return String.format("""
                Type: %s
                Price: R$ %.2f 
                """, typeName, typeValue);
    }

    public double getTypeValue() {
        return typeValue;
    }
}
