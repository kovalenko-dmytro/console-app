package com.kovalenko.application.info.entity;

import java.util.List;

public class Info {

    private String api;
    private String description;
    private List<OperationParameter> operationParameters;

    public Info() {
    }

    public Info(String api, String description) {
        this.api = api;
        this.description = description;
    }

    public Info(String api, String description, List<OperationParameter> operationParameters) {
        this(api, description);
        this.operationParameters = operationParameters;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OperationParameter> getOperationParameters() {
        return operationParameters;
    }

    public void setOperationParameters(List<OperationParameter> operationParameters) {
        this.operationParameters = operationParameters;
    }
}
