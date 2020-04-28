package com.kovalenko.application.info.entity;

public class OperationParameter {

    private String name;
    private String description;

    public OperationParameter() {
    }

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

    public void setDescription(String description) {
        this.description = description;
    }
}