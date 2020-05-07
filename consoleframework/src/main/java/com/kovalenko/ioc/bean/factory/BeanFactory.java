package com.kovalenko.ioc.bean.factory;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.ioc.bean.factory.stereotype.Controller;
import com.kovalenko.ioc.bean.injector.BeanInjector;
import com.kovalenko.ioc.bean.scanner.BeanScanner;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanFactory {

    private BeanScanner beanScanner;
    private BeanInjector beanInjector;
    private MessageSource messageSource = SystemMessageSource.getInstance();

    private Map<String, Object> beans = new HashMap<>();

    private BeanFactory() {
        beanScanner = new BeanScanner();
        beanInjector = new BeanInjector();
    }

    private static class BeanFactoryHolder {
        private static final BeanFactory instance = new BeanFactory();
    }

    public static BeanFactory getInstance() {
        return BeanFactoryHolder.instance;
    }

    public void init(String packageName) throws BeanCreationException {
        beanScanner.scanPackage(packageName, beans);
        beanInjector.injectDependencies(beans);
    }

    public Map<String, Object> getBeans() throws BeanCreationException {
        checkInitBeans();
        return beans;
    }

    public void clearBeanContainer() {
        beans.clear();
    }

    public List<Object> getControllers() throws BeanCreationException {
        checkInitBeans();
        return beans.values()
            .stream()
            .filter(bean -> bean.getClass().isAnnotationPresent(Controller.class))
            .collect(Collectors.toList());
    }

    private void checkInitBeans() throws BeanCreationException {
        if (beans.isEmpty()) {
            throw new BeanCreationException(messageSource.getMessage("error.any.beans.not.found"));
        }
    }
}
