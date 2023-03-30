package com.gersonfaneto.yams.models.billing.payment;

public enum PaymentType {
    Credit("Credit"),
    Transfer("Transfer"),
    Cash("Cash");

    private final String typeName;

    PaymentType(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return String.format("Type: %s", typeName);
    }

    public String getTypeName() {
        return typeName;
    }
}
