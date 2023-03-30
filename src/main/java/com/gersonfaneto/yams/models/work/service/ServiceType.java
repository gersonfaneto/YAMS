package com.gersonfaneto.yams.models.work.service;

public enum ServiceType {
    SimpleFormatting("Simple Formatting", 50.00),
    CompleteFormatting("Complete Formatting", 60.00),
    ProgramsInstallation("Programs Installation", 10.00),
    Assembly("Assembly", 0.00),
    Cleaning("Cleaning", 70.00);

    private final String typeName;
    private final double typeValue;

    ServiceType(String typeName, double typeValue) {
        this.typeName = typeName;
        this.typeValue = typeValue;
    }

    @Override
    public String toString() {
        return String.format("""
                Type: %s
                Value: R$ %.2f
                """, typeName, typeValue);
    }

    public String getTypeName() {
        return typeName;
    }

    public double getTypeValue() {
        return typeValue;
    }
}
