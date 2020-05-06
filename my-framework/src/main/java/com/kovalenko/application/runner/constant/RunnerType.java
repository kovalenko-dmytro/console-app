package com.kovalenko.application.runner.constant;

import java.util.Arrays;
import java.util.Set;

public enum RunnerType {

    CONSOLE("-console"),
    SCRIPT("-script"),
    XML("-xml");

    private String value;

    RunnerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RunnerType findRunnerType(Set<String> argKeys) {
        return Arrays.stream(RunnerType.values())
            .filter(runnerType -> argKeys.contains(runnerType.getValue()))
            .findFirst()
            .orElse(CONSOLE);
    }
}
