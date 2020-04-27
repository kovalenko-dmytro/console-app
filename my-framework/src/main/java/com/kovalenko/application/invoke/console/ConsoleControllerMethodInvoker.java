package com.kovalenko.application.invoke.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleControllerMethodInvoker implements Invoker<RequestPathMatchResult, ConsoleRequest> {

    private MessageSource messageSource = SystemMessageSource.getInstance();

    @Override
    public void invoke(RequestPathMatchResult matchResult, ConsoleRequest consoleRequest) throws ApplicationException {
        Method pathMatchMethod = matchResult.getRequestPathMethod();
        try {
            pathMatchMethod.invoke(matchResult.getController(), mapRequestParameters(pathMatchMethod, consoleRequest));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ApplicationException(messageSource.getMessage("error.cannot.invoke.controller.method", matchResult.getRequestPathMethod().getName()));
        }
    }

    private Object[] mapRequestParameters(Method requestPathMethod, ConsoleRequest request) throws ApplicationException {
        List<Object> result = new ArrayList<>();
        for (Parameter parameter: requestPathMethod.getParameters()) {
            if (parameter.isAnnotationPresent(PathVariable.class)) {
                String name = parameter.getAnnotation(PathVariable.class).name();
                String value = Optional
                    .ofNullable(request.getRequestParameters().get(name))
                    .orElseThrow(() -> new ApplicationException(messageSource.getMessage("error.cannot.resolve.path.variable", name, requestPathMethod.getAnnotation(RequestMapping.class).path())));
                result.add(value);
            }
        }
        return result.toArray();
    }
}
