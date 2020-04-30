package com.kovalenko.application.info.entity;

import com.kovalenko.application.info.constant.ApiInfoConstant;

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

    public void print() {
        System.out.println(ApiInfoConstant.API_DECLARATION.getValue().concat(api));
        System.out.println(ApiInfoConstant.API_DESCRIPTION.getValue().concat(description));
        operationParameters.forEach(operationParam -> System.out.println(
            ApiInfoConstant.API_PATH_VAR_DECLARATION.getValue()
                .concat(operationParam.getName())
                .concat(ApiInfoConstant.API_PATH_VAR_DESCRIPTION.getValue())
                .concat(operationParam.getDescription())));
        System.out.println(ApiInfoConstant.API_DELIMITER.getValue());
    }
}
