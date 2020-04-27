package com.kovalenko.ioc.bean.injector;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.ioc.bean.factory.annotation.Autowired;
import com.kovalenko.ioc.constant.ContainerConstant;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BeanInjector {

    private MessageSource messageSource = SystemMessageSource.getInstance();

    public void injectDependencies(Map<String, Object> beans) throws BeanCreationException {
        for (Object bean: beans.values()) {
            for (Field field: bean.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    if (field.getType().isInterface()) {
                        findFieldQualifierDependency(beans, bean, field);
                    } else {
                        findFieldDependency(beans, bean, field);
                    }
                }
            }
        }
    }

    private void findFieldQualifierDependency(Map<String, Object> beans, Object bean, Field field) throws BeanCreationException {
        List<Object> dependencies = findFieldTypeBeanImplementations(beans, field);
        if (dependencies.isEmpty()) {
            throw new BeanCreationException(messageSource.getMessage("error.cannot.find.dependency", field.getName()));
        }
        if (dependencies.size() > 1) {
            String fullQualifier = field.getAnnotation(Autowired.class).fullQualifier();
            if (fullQualifier.isBlank()) {
                throw new BeanCreationException(messageSource.getMessage("error.qualifier.not.defined"));
            }
            Object dependency = Optional
                .ofNullable(beans.get(fullQualifier))
                .orElseThrow(() -> new BeanCreationException(messageSource.getMessage("error.cannot.find.qualifier", fullQualifier, field.getName())));
            injectBeanDependency(bean, field, dependency);
        } else {
            injectBeanDependency(bean, field, dependencies.get(0));
        }
    }

    private List<Object> findFieldTypeBeanImplementations(Map<String, Object> beans, Field field) {
        return beans.values().stream()
            .filter(impl ->
                Arrays.stream(impl.getClass().getInterfaces()).anyMatch(filterByInterfaceEqual(field)) ||
                    Arrays.stream(impl.getClass().getSuperclass().getInterfaces()).anyMatch(filterByInterfaceEqual(field)))
            .collect(Collectors.toList());
    }

    private Predicate<Class<?>> filterByInterfaceEqual(Field field) {
        return interfaceClass -> interfaceClass.equals(field.getType());
    }

    private void findFieldDependency(Map<String, Object> beans, Object bean, Field field) throws BeanCreationException {
        Object dependency = Optional
            .ofNullable(beans.get(field.getType().getCanonicalName()))
            .orElseThrow(() -> new BeanCreationException(messageSource.getMessage("error.cannot.find.dependency", field.getName())));
        injectBeanDependency(bean, field, dependency);
    }

    private void injectBeanDependency(Object bean, Field field, Object dependency) throws BeanCreationException {
        String setterName = ContainerConstant.SETTER_PREFIX.getValue()
            .concat(field.getName().substring(0, 1).toUpperCase())
            .concat(field.getName().substring(1));
        Method setter;
        try {
            setter = bean.getClass().getMethod(setterName, field.getType());
            setter.invoke(bean, dependency);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new BeanCreationException(messageSource.getMessage("error.cannot.inject.dependency", bean.getClass().toString()));
        }
    }
}
