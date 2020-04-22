package com.kovalenko.application.run.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.info.ApiInfo;
import com.kovalenko.application.info.console.ConsoleApiInfo;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.RequestReader;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.console.ConsoleRequestReader;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.invoke.console.ConsoleControllerMethodInvoker;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.console.ConsoleControllerResolver;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.application.run.Runner;
import com.kovalenko.application.run.constant.RunnerConstant;
import com.kovalenko.application.validate.Validator;
import com.kovalenko.application.validate.console.ControllerMethodArgsValidator;
import com.kovalenko.ioc.exception.BeanCreationException;

public class ConsoleRunner implements Runner {

    private RequestReader<String> reader;
    private RequestParser<ConsoleRequest> parser;
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver;
    private Validator<RequestPathMatchResult, ConsoleRequest> validator;
    private Invoker<RequestPathMatchResult, ConsoleRequest> invoker;
    private ApiInfo apiInfo;

    public ConsoleRunner() {
        reader = new ConsoleRequestReader();
        parser = new ConsoleRequestParser();
        resolver = new ConsoleControllerResolver();
        validator = new ControllerMethodArgsValidator();
        invoker = new ConsoleControllerMethodInvoker();
        apiInfo = new ConsoleApiInfo();
    }

    @Override
    public void run(String ... args) {
        printPreview();
        String input;
        while (true) {
            input = reader.read();
            if (checkExit(input)) { break; }
            process(input);
        }
    }

    private void printPreview() {
        System.out.println(RunnerConstant.PLEASE_ENTER_REQUEST_COMMAND.getValue());
        System.out.println(RunnerConstant.INFO_TO_VIEW_AVAILABLE_API_INFO.getValue());
        System.out.println(RunnerConstant.EXIT_TO_EXIT_FROM_PROGRAM.getValue());
    }

    private void process(String input) {
        try {
            if (checkInfo(input)) {
                apiInfo.getInfo();
                return;
            }
            ConsoleRequest request = parser.parse(input);
            RequestPathMatchResult pathMatchResult = resolver.resolve(request);
            validator.validate(pathMatchResult, request);
            invoker.invoke(pathMatchResult, request);
        } catch (ApplicationException | BeanCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkInfo(String input) {
        return RunnerConstant.INFO_COMMAND_NAME.getValue().equalsIgnoreCase(input);
    }

    private boolean checkExit(String input) {
        return RunnerConstant.EXIT_COMMAND_NAME.getValue().equalsIgnoreCase(input);
    }
}
