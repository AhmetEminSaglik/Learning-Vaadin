package org.vaadin.aes.enums;

public enum EnumPaymentMethod {
    CASH("Cash"), CARD("Card");

    private final String name;

    EnumPaymentMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
