package org.vaadin.aes.enums;

public enum EnumSessionData {
    ORDER_LIST("orderList");
    private final String name;

    EnumSessionData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
