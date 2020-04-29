package bean.controller;

import bean.service.FirstTestService;
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

@Controller
public class TestController {

    @Autowired
    private FirstTestService firstTestService;

    @RequestMapping(path = "test {-param1} {-param2} {-param3}")
    @OperationInfo(
        api = "test {-param1} {-param2} {-param3}",
        description = "test api",
        values = @OperationParams(value = {
            @OperationParam(name = "-param1", description = "test String param1"),
            @OperationParam(name = "-param2", description = "test String param2"),
            @OperationParam(name = "-param3", description = "test String param3")}))
    public void test1(@PathVariable(name = "-param1") @NotNull String param1,
                        @PathVariable(name = "-param2") @NotEmpty String param2,
                        @PathVariable(name = "-param3") @FilePath String param3) {
        firstTestService.test(param1, param2, param3);
    }

    @RequestMapping(path = "test {-param}")
    public void test2(@PathVariable(name = "param") String param) {

    }

    @RequestMapping(path = "check path var {-param}")
    public void test3(String param) {

    }
}
