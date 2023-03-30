package com.gersonfaneto.yams.models.entities.user;

public enum UserType {
    Normal("Normal"),
    Administrator("Administrator");

    private final String typeName;

    UserType(String typeName) {
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
