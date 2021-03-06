package com.kovalenko.application.resolve.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.application.resolve.constant.ResolveConstant;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConsoleControllerResolver implements Resolver<ConsoleRequest, RequestPathMatchResult> {

    private MessageSource messageSource = SystemMessageSource.getInstance();

    @Override
    public RequestPathMatchResult resolve(ConsoleRequest request) throws ApplicationException, BeanCreationException {
        List<Object> controllers = BeanFactory.getInstance().getControllers();
        return findRequestPathMatch(controllers, request);
    }

    private RequestPathMatchResult findRequestPathMatch(List<Object> controllers, ConsoleRequest request) throws ApplicationException {
        String requestPath = resolveRequestPath(request);
        for (Object controller: controllers) {
            for (Method method: controller.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(RequestMapping.class)
                    && method.getAnnotation(RequestMapping.class).path().equalsIgnoreCase(requestPath)) {
                    checkPathVariables(method, request);
                    RequestPathMatchResult result = new RequestPathMatchResult();
                    result.setController(controller);
                    result.setRequestPathMethod(method);
                    result.setParameterTypes(method.getParameterTypes());
                    return result;
                }
            }
        }
        throw new ApplicationException(messageSource.getMessage("error.cannot.resolve.request.path", requestPath));
    }

    private void checkPathVariables(Method method, ConsoleRequest request) throws ApplicationException {
        checkDuplicatePathVariablesNames(method.getParameters());
        for (String requestParam: request.getRequestParameters().keySet()) {
                Arrays.stream(method.getParameters())
                    .filter(filterByPathVariableAnnotation(requestParam))
                    .findFirst()
                    .orElseThrow(() -> new ApplicationException(messageSource.getMessage("error.cannot.resolve.path.variable", requestParam, method.getAnnotation(RequestMapping.class).path())));
        }
    }

    private void checkDuplicatePathVariablesNames(Parameter[] parameters) throws ApplicationException {
        boolean areAllUnique = Arrays.stream(parameters)
            .filter(parameter -> parameter.isAnnotationPresent(PathVariable.class))
            .map(parameter -> parameter.getAnnotation(PathVariable.class).name())
            .allMatch(new HashSet<>()::add);
        if (!areAllUnique) {
            throw new ApplicationException(messageSource.getMessage("error.cannot.duplicate.path.var.names", PathVariable.class.getSimpleName()));
        }
    }

    private Predicate<Parameter> filterByPathVariableAnnotation(String requestParam) {
        return parameter ->
            parameter.isAnnotationPresent(PathVariable.class) &&
                requestParam.equalsIgnoreCase(parameter.getAnnotation(PathVariable.class).name());
    }

    private String resolveRequestPath(ConsoleRequest request) {
        return request.getRequestPath()
            .concat(ResolveConstant.SPACE.getValue())
            .concat(
                request.getRequestParameters().keySet()
                    .stream()
                    .map(pathVariable ->
                        ResolveConstant.OPEN_CURL.getValue()
                            .concat(pathVariable)
                            .concat(ResolveConstant.CLOSE_CURL.getValue()))
                    .collect(Collectors.joining(ResolveConstant.SPACE.getValue())));
    }
}
