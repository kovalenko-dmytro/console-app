package com.kovalenko.application.configuration.constant;

public enum ConfigurationConstant {

    CONFIGURATION_PROPERTIES_DEFAULT_FILE_PATH("/application.properties"),
    KEY_PREFIX("-"),
    CONFIG_KEY("-config"),
    ARG_ASSIGN("=");

    private String value;

    ConfigurationConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
