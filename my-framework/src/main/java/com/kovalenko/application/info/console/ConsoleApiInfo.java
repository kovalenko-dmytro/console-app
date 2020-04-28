package com.kovalenko.application.info.console;

import com.kovalenko.application.info.ApiInfo;
import com.kovalenko.application.info.annotation.OperationInfo;
import com.kovalenko.application.info.annotation.OperationParam;
import com.kovalenko.application.info.constant.ApiInfoConstant;
import com.kovalenko.application.info.entity.Info;
import com.kovalenko.application.info.entity.OperationParameter;
import com.kovalenko.application.resolve.annotation.RequestMapping;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleApiInfo implements ApiInfo {

    @Override
    public List<Info> getInfo() throws BeanCreationException {
        List<Method> methods = findRequestMappingMethods();
        List<Info> result = new ArrayList<>();
        for (Method method: methods) {
            if (method.isAnnotationPresent(OperationInfo.class)) {
                result.add(getDeclaredInfo(method));
            } else {
                result.add(getDefaultInfo(method));
            }
        }
        return result;
    }

    private Info getDeclaredInfo(Method method) {
        OperationInfo operationInfo = method.getAnnotation(OperationInfo.class);
        return new Info(operationInfo.api(), operationInfo.description(), getOperationParameters(operationInfo));
    }

    private List<OperationParameter> getOperationParameters(OperationInfo operationInfo) {
        OperationParam[] operationParams = operationInfo.values().value();
        return Arrays.stream(operationParams)
            .map(param -> new OperationParameter(param.name(), param.description()))
            .collect(Collectors.toList());
    }

    private Info getDefaultInfo(Method method) {
        return new Info(method.getAnnotation(RequestMapping.class).path(), ApiInfoConstant.API_DEFAULT_DESCRIPTION.getValue());
    }

    private List<Method> findRequestMappingMethods() throws BeanCreationException {
        return BeanFactory.getInstance().getControllers().stream()
            .flatMap(controller ->
                Arrays.stream(controller.getClass().getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(RequestMapping.class)))
            .collect(Collectors.toList());
    }
}
