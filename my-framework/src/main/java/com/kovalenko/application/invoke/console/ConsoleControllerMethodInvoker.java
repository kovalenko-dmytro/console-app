package com.kovalenko.application.invoke.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.ioc.constant.ErrorMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleControllerMethodInvoker implements Invoker<RequestPathMatchResult, ConsoleRequest> {

    @Override
    public void invoke(RequestPathMatchResult matchResult, ConsoleRequest consoleRequest) throws ApplicationException {
        Method pathMatchMethod = matchResult.getRequestPathMethod();
        try {
            pathMatchMethod.invoke(matchResult.getController(), mapRequestParameters(pathMatchMethod, consoleRequest));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ApplicationException(ErrorMessage.CANNOT_INVOKE_CONTROLLER_METHOD.getValue() + matchResult.getRequestPathMethod().getName());
        }
    }

    private Object[] mapRequestParameters(Method requestPathMethod, ConsoleRequest request) throws ApplicationException {
        List<Object> result = new ArrayList<>();
        for (Parameter parameter: requestPathMethod.getParameters()) {
            if (parameter.isAnnotationPresent(PathVariable.class)) {
                String name = parameter.getAnnotation(PathVariable.class).name();
                String value = request.getRequestParameters().get(name);
                result.add(
                    Optional
                        .ofNullable(value)
                        .orElseThrow(() -> new ApplicationException(MessageFormat.format(
                            ErrorMessage.CANNOT_RESOLVE_PATH_VARIABLE.getValue(),
                            name,
                            requestPathMethod.getAnnotation(RequestMapping.class).path()))));
            }
        }
        return result.toArray();
    }
}
