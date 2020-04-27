package com.kovalenko.controller;

import com.kovalenko.application.info.annotation.OperationInfo;
import com.kovalenko.application.info.annotation.OperationParam;
import com.kovalenko.application.info.annotation.OperationParams;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.application.validate.constraint.annotation.NotBlank;
import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;
import com.kovalenko.service.one.FirstService;
import com.kovalenko.service.one.ServiceWithoutInterface;

@Controller
public class FirstController {

    @Autowired(fullQualifier = "com.kovalenko.service.one.FirstServiceImpl")
    private FirstService firstService;
    @Autowired
    private ServiceWithoutInterface serviceWithoutInterface;
    @Autowired
    private MessageSource messageSource;

    @RequestMapping(path = "first {-param}")
    @OperationInfo(
        api = "first {-param}",
        description = "test api first",
        values = @OperationParams(value = {@OperationParam(name = "-param", description = "test String param")}))
    public void first(@PathVariable(name = "-param") @NotBlank String param) {
        firstService.first(param);
        serviceWithoutInterface.doSomething();
        System.out.println(messageSource.getMessage("test.test.test", param));
    }
}
