package com.kovalenko.controller;

import com.kovalenko.application.info.annotation.OperationInfo;
import com.kovalenko.application.info.annotation.OperationParam;
import com.kovalenko.application.info.annotation.OperationParams;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.application.validate.constraint.annotation.NotBlank;
import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;
import com.kovalenko.service.one.FirstService;

@Controller
public class FirstController {

    @Autowired(fullQualifier = "com.kovalenko.service.one.FirstServiceImpl")
    private FirstService firstService;

    @RequestMapping(path = "first {-param}")
    @OperationInfo(
        api = "first {-param}",
        description = "test api first",
        values = @OperationParams(value = {@OperationParam(name = "-param", description = "test String param")}))
    public void first(@PathVariable(name = "-param") @NotBlank String param) {
        firstService.first(param);
    }

    public void setFirstService(FirstService firstService) {
        this.firstService = firstService;
    }
}
