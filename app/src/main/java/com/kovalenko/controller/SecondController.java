package com.kovalenko.controller;

import com.kovalenko.application.info.annotation.OperationInfo;
import com.kovalenko.application.info.annotation.OperationParam;
import com.kovalenko.application.info.annotation.OperationParams;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.application.validate.constraint.annotation.FilePath;
import com.kovalenko.application.validate.constraint.annotation.NotEmpty;
import com.kovalenko.application.validate.constraint.annotation.NotNull;
import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;
import com.kovalenko.service.two.SecondService;

@Controller
public class SecondController {

    @Autowired(fullQualifier = "com.kovalenko.service.two.SecondServiceImpl")
    private SecondService secondService;

    @RequestMapping(path = "second {-param1} {-param2}")
    @OperationInfo(
        api = "second {-param1} {-param2}",
        description = "test api second_1",
        values = @OperationParams(value = {
            @OperationParam(name = "-param1", description = "test String param1"),
            @OperationParam(name = "-param2", description = "test String param2")}))
    public void second1(@PathVariable(name = "-param1") @NotNull String param1,
                        @PathVariable(name = "-param2") @NotEmpty String param2) {
        secondService.second1(param1, param2);
    }

    @RequestMapping(path = "second {-file}")
    @OperationInfo(
        api = "second {-file}",
        description = "test api second_2",
        values = @OperationParams(value = {@OperationParam(name = "-file", description = "test file path")}))
    public void second2(@PathVariable(name = "-file") @FilePath String filePath) {
        secondService.second2(filePath);
    }

    public void setSecondService(SecondService secondService) {
        this.secondService = secondService;
    }
}
