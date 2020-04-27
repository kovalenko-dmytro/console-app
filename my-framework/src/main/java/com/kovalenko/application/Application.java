package com.kovalenko.application;

import com.kovalenko.application.configuration.ArgumentsParser;
import com.kovalenko.application.configuration.console.ConsoleArgumentsParser;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.application.runner.ApplicationRunner;
import com.kovalenko.ioc.annotation.ConsoleApplication;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.Map;

public class Application {

    private static final ArgumentsParser argumentsParser = new ConsoleArgumentsParser();

    private Application(){}

    public static void launch(Class clazz, String... args) {
        try {
            Map<String, String> arguments = argumentsParser.parse(args);
            instantiateBeans(clazz);
            ApplicationRunner.getInstance().run(arguments);
        } catch (ApplicationException | BeanCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void instantiateBeans(Class clazz) throws BeanCreationException {
        String scanPackage = getScanPackageAttribute(clazz);
        BeanFactory.getInstance().init(scanPackage);
    }

    private static String getScanPackageAttribute(Class clazz) throws BeanCreationException {
        ConsoleApplication consoleApplication;
        if (clazz.isAnnotationPresent(ConsoleApplication.class)) {
            consoleApplication = (ConsoleApplication) clazz.getAnnotation(ConsoleApplication.class);
            return consoleApplication.scanPackage().isBlank() ? clazz.getPackageName() : consoleApplication.scanPackage();
        }
        throw new BeanCreationException(SystemMessageSource.getInstance().getMessage("error.annotation.wasnt.defined", ConsoleApplication.class));
    }
}
