package org.vaadin.aes.enums;

public enum EnumSessionData {
    USER_DATA("user"),
    PAYMENT_METHOD("payment_method"),
    ORDER_CONCEPT_LIST("orderConceptList"),
    PAYMENT("payment"),
    ORDER("order"),;
    private final String name;

    EnumSessionData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
