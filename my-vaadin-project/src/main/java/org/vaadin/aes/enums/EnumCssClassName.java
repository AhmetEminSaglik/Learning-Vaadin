package org.vaadin.aes.enums;

public enum EnumCssClassName {
    BTN("btn"),
    BTN_PRIMARY("btn-primary")
    ;

    private  final  String name;

    EnumCssClassName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "EnumCssClassName{" +
                "name='" + name + '\'' +
                '}';
    }
}
