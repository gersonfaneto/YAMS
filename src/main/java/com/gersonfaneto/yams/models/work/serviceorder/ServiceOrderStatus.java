package com.gersonfaneto.techinfo.models.work.serviceorder;

public enum ServiceOrderStatus {
    Created("Created"),
    Open("Open"),
    Finished("Finished"),
    Canceled("Canceled"),
    PaymentPending("Payment Pending"),
    Payed("Payed");

    private final String typeName;

    ServiceOrderStatus(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return String.format("Status: %s", typeName);
    }

    public String getTypeName() {
        return typeName;
    }
}
