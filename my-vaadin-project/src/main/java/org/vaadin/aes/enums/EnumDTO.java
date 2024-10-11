package org.vaadin.aes.enums;

public enum EnumDTO {
    USER_DATA("userData");
    private  final String name;

    EnumDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
