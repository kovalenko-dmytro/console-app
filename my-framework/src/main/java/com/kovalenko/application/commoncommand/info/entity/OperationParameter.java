package com.kovalenko.application.commoncommand.info.entity;

public class OperationParameter {

    private String name;
    private String description;

    public OperationParameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
}
