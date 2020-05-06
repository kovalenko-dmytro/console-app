package com.kovalenko.application.commoncommand;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum  CommonCommandType {

    INFO("info", "<info> to view available API info"),
    EXIT("exit", "<exit> to exit from program"),
    UNDEFINED("undefined", "undefined");

    private String value;
    private String description;

    CommonCommandType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static List<String> getDescriptions() {
        return Arrays.stream(CommonCommandType.values())
            .filter(commonCommandType -> !commonCommandType.equals(UNDEFINED))
            .map(CommonCommandType::getDescription)
            .collect(Collectors.toList());
    }

    public static boolean contains(String requestPath) {
        return Arrays.stream(CommonCommandType.values())
            .map(CommonCommandType::getValue)
            .collect(Collectors.toList())
            .contains(requestPath);
    }

    public static CommonCommandType findCommonCommandType(String requestPath) {
        return Arrays.stream(CommonCommandType.values())
            .filter(commonCommandType -> commonCommandType.getValue().equalsIgnoreCase(requestPath))
            .findFirst()
            .orElse(UNDEFINED);
    }
}
