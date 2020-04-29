package com.kovalenko.application.runner.constant;

import java.util.Arrays;
import java.util.Objects;

public enum RunnerType {

    CONSOLE("console"),
    SCRIPT("-script"),
    XML("-xml"),
    UNDEFINED("undefined");

    private String value;

    RunnerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RunnerType getType(String name) {
        return Objects.isNull(name)
            ? CONSOLE
            : Arrays.stream(RunnerType.values()).filter(type -> type.getValue().equalsIgnoreCase(name)).findFirst().orElse(UNDEFINED);
    }
}
