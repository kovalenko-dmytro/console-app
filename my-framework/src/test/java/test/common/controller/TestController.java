package test.common.controller;

import com.kovalenko.application.info.annotation.OperationInfo;
import com.kovalenko.application.info.annotation.OperationParam;
import com.kovalenko.application.info.annotation.OperationParams;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.application.validate.constraint.annotation.FilePath;
import com.kovalenko.application.validate.constraint.annotation.NotBlank;
import com.kovalenko.application.validate.constraint.annotation.NotEmpty;
import com.kovalenko.application.validate.constraint.annotation.NotNull;
import com.kovalenko.application.view.view.ConsoleView;
import com.kovalenko.application.view.view.ResponseStatus;
import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;
import test.common.service.FirstTestService;
import test.common.view.TestView;

import java.util.List;

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
    public void test2(@PathVariable(name = "param") String param) {}

    @RequestMapping(path = "test not null {-param}")
    public void testNotNullParam(@PathVariable(name = "-param") @NotNull String param) {

    }

    @RequestMapping(path = "test not empty {-param}")
    public void testNotEmptyParam(@PathVariable(name = "-param") @NotEmpty String param) {

    }

    @RequestMapping(path = "test not blank {-param}")
    public void testNotBlankParam(@PathVariable(name = "-param") @NotBlank String param) {

    }

    @RequestMapping(path = "test not file path {-param}")
    public void testFilePathParam(@PathVariable(name = "-param") @FilePath String param) {

    }

    @RequestMapping(path = "check path var {-param}")
    public void test3(String param) {

    }

    @RequestMapping(path = "test void {-param}")
    public void testVoid(@PathVariable(name = "-param") @NotNull String param) {

    }

    @RequestMapping(path = "test string {-param}")
    public String testReturnString(@PathVariable(name = "-param") @NotNull String param) {
        return param;
    }

    @RequestMapping(path = "test view {-param1} {-param2} {-param3}")
    public ConsoleView<List<String>> testVoid(@PathVariable(name = "-param1") @NotNull String param1,
                                              @PathVariable(name = "-param2") @NotNull String param2,
                                              @PathVariable(name = "-param3") @NotNull String param3) {
        ConsoleView<List<String>> view = new TestView();
        view.setResponseStatus(ResponseStatus.OK);
        view.setErrorMessage("no error message");
        view.setBody(List.of(param1, param2, param3));
        return view;
    }
}
