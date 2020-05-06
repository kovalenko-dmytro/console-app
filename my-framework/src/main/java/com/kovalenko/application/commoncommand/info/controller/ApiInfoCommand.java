package com.kovalenko.application.commoncommand.info.controller;

import com.kovalenko.application.commoncommand.CommonCommand;
import com.kovalenko.application.commoncommand.info.entity.Info;
import com.kovalenko.application.commoncommand.info.service.ApiInfo;
import com.kovalenko.application.commoncommand.info.service.console.ConsoleApiInfo;
import com.kovalenko.application.commoncommand.info.view.ApiInfoView;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.view.view.ConsoleView;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.List;

public class ApiInfoCommand implements CommonCommand<List<Info>> {

    private ApiInfo apiInfo = new ConsoleApiInfo();

    @Override
    public ConsoleView<List<Info>> execute() {
        ConsoleView<List<Info>> view = new ApiInfoView();
        try {
            List<Info> infos = apiInfo.getInfo();
            view.setBody(infos);
        } catch (BeanCreationException | ApplicationException e) {
            view.setErrorMessage(e.getMessage());
        }
        return view;
    }
}
