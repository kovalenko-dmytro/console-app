package com.kovalenko.application.runner;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.info.ApiInfo;
import com.kovalenko.application.info.console.ConsoleApiInfo;
import com.kovalenko.application.info.entity.Info;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.invoke.console.ConsoleControllerMethodInvoker;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.console.ConsoleControllerResolver;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.application.runner.constant.RunnerConstant;
import com.kovalenko.application.runner.constant.RunnerType;
import com.kovalenko.application.runner.factory.CommandProvider;
import com.kovalenko.application.runner.factory.CommandProviderFactory;
import com.kovalenko.application.validate.Validator;
import com.kovalenko.application.validate.console.ControllerMethodArgsValidator;
import com.kovalenko.application.view.render.ViewRenderer;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.List;
import java.util.Map;

public class ApplicationRunner {

    private RequestParser<ConsoleRequest> requestParser;
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver;
    private Validator<RequestPathMatchResult, ConsoleRequest> validator;
    private Invoker<RequestPathMatchResult, ConsoleRequest> invoker;
    private ViewRenderer renderer;
    private ApiInfo apiInfo;

    private ApplicationRunner() {
        requestParser = new ConsoleRequestParser();
        resolver = new ConsoleControllerResolver();
        validator = new ControllerMethodArgsValidator();
        invoker = new ConsoleControllerMethodInvoker();
        renderer = new ViewRenderer();
        apiInfo = new ConsoleApiInfo();
    }

    private static class RunnerHolder {
        private static final ApplicationRunner instance = new ApplicationRunner();
    }

    public static ApplicationRunner getInstance() {
        return RunnerHolder.instance;
    }

    public void run(Map<String, String> arguments) throws ApplicationException {
        RunnerType runnerType = RunnerType.findRunnerType(arguments.keySet());
        CommandProvider provider = CommandProviderFactory.getProvider(runnerType);
        String input;
        while (true) {
            input = provider.nextCommand().trim();
            if (checkExit(input)) { break; }
            process(input);
        }
    }

    private void process(String input) {
        try {
            if (checkInfo(input)) {
                List<Info> info = apiInfo.getInfo();
                info.forEach(Info::print);
                return;
            }
            ConsoleRequest request = requestParser.parse(input);
            RequestPathMatchResult pathMatchResult = resolver.resolve(request);
            validator.validate(pathMatchResult, request);
            Object response = invoker.invoke(pathMatchResult, request);
            renderer.render(response);
        } catch (ApplicationException | BeanCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean checkExit(String input) {
        return RunnerConstant.EXIT_COMMAND_NAME.getValue().equalsIgnoreCase(input);
    }

    private boolean checkInfo(String input) {
        return RunnerConstant.INFO_COMMAND_NAME.getValue().equalsIgnoreCase(input);
    }
}
