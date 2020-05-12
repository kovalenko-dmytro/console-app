package com.kovalenko.ioc.constant;

public enum ContainerConstant {

    DOT("."),
    SLASH("/"),
    CLASS_EXTENSION(".class");

    private String value;

    ContainerConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
