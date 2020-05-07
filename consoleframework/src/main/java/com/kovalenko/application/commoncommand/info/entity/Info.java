package com.kovalenko.application.commoncommand.info.entity;

import java.util.List;

public class Info {

    private String api;
    private String description;
    private List<OperationParameter> operationParameters;

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

    public List<OperationParameter> getOperationParameters() {
        return operationParameters;
    }
}
