package com.kovalenko.application.runner.constant;

public enum RunnerConstant {

    PLEASE_ENTER_REQUEST_COMMAND("-------- Please enter request command --------");

    private String value;

    RunnerConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
