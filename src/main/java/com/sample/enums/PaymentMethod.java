package com.sample.enums;

public enum PaymentMethod {

    CASH ("Cash"),
    CREDIT_CARD ("Credit Card");

    private String displayName;

    PaymentMethod(String name) {
        this.displayName = name;
    }

    public String getDisplayName() {
        return displayName;
    }

}
