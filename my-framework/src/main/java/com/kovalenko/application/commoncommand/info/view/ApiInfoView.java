package com.kovalenko.application.commoncommand.info.view;

import com.kovalenko.application.commoncommand.info.entity.Info;
import com.kovalenko.application.commoncommand.info.service.constant.ApiInfoConstant;
import com.kovalenko.application.view.view.ConsoleView;

import java.util.List;
import java.util.Objects;

public class ApiInfoView extends ConsoleView<List<Info>> {

    @Override
    public void render() {
        if (Objects.nonNull(getErrorMessage())) {
            System.out.println(getErrorMessage());
        }
        if (Objects.nonNull(getBody())) {
            getBody().forEach(info -> {
                System.out.println(ApiInfoConstant.API_DECLARATION.getValue().concat(info.getApi()));
                System.out.println(ApiInfoConstant.API_DESCRIPTION.getValue().concat(info.getDescription()));

                info.getOperationParameters().forEach(operationParam -> System.out.println(
                    ApiInfoConstant.API_PATH_VAR_DECLARATION.getValue()
                        .concat(operationParam.getName())
                        .concat(ApiInfoConstant.API_PATH_VAR_DESCRIPTION.getValue())
                        .concat(operationParam.getDescription())));

                System.out.println(ApiInfoConstant.API_DELIMITER.getValue());
            });
        }
    }
}
