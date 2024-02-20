package org.example.enums;

public enum Status {
    PENDING("PENDING"),
    APPROVED("APPROVED");

    private String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
