package com.kovalenko.application.runner;

import com.kovalenko.application.commoncommand.CommonCommandExecutor;
import com.kovalenko.application.commoncommand.CommonCommandType;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.invoke.console.ConsoleControllerMethodInvoker;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.console.ConsoleControllerResolver;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.application.runner.constant.RunnerType;
import com.kovalenko.application.runner.factory.CommandProvider;
import com.kovalenko.application.runner.factory.CommandProviderFactory;
import com.kovalenko.application.validate.Validator;
import com.kovalenko.application.validate.console.ControllerMethodArgsValidator;
import com.kovalenko.application.view.render.ViewRenderer;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.Map;

public class ApplicationRunner {

    private RequestParser<ConsoleRequest> requestParser;
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver;
    private Validator<RequestPathMatchResult, ConsoleRequest> validator;
    private Invoker<RequestPathMatchResult, ConsoleRequest> invoker;
    private ViewRenderer renderer;
    private CommonCommandExecutor commonCommandExecutor;

    private ApplicationRunner() {
        requestParser = new ConsoleRequestParser();
        resolver = new ConsoleControllerResolver();
        validator = new ControllerMethodArgsValidator();
        invoker = new ConsoleControllerMethodInvoker();
        renderer = new ViewRenderer();
        commonCommandExecutor = new CommonCommandExecutor();
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
        ConsoleRequest request;
        while (true) {
            input = provider.nextCommand().trim();
            if (CommonCommandType.EXIT.getValue().equalsIgnoreCase(input)) { break; }
            request = requestParser.parse(input);
            processClientRequest(request);
        }
    }

    private void processClientRequest(ConsoleRequest request) {
        try {
            if (CommonCommandType.contains(request.getRequestPath())) {
                commonCommandExecutor.execute(request);
            } else {
                RequestPathMatchResult pathMatchResult = resolver.resolve(request);
                validator.validate(pathMatchResult, request);
                Object response = invoker.invoke(pathMatchResult, request);
                renderer.render(response);
            }
        } catch (ApplicationException | BeanCreationException e) {
            System.out.println(e.getMessage());
        }
    }
}
