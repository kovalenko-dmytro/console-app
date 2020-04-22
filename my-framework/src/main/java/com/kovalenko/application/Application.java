package com.kovalenko.application;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.runner.Runner;
import com.kovalenko.application.runner.RunnerFactory;
import com.kovalenko.ioc.annotation.ScanPackage;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.bean.factory.stereotype.Launcher;
import com.kovalenko.ioc.constant.ErrorMessage;
import com.kovalenko.ioc.exception.BeanCreationException;

import java.util.List;
import java.util.stream.Collectors;

public class Application {

    private Application(){}

    public static void launch(Class clazz, String... args) {
        try {
            instantiateBeans(clazz);
            Object launcher = getCurrentLauncher(clazz);
            Runner runner = RunnerFactory.getRunner(launcher.getClass().getAnnotation(Launcher.class).launchType());
            runner.run(args);
        } catch (ApplicationException | BeanCreationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Object getCurrentLauncher(Class clazz) throws BeanCreationException {
        List<Object> launchers = findLaunchers(clazz);
        if (launchers.size() > 1) {
            throw new BeanCreationException(ErrorMessage.CANNOT_MORE_THAN_ONE_LAUNCHER.getValue());
        }
        return launchers.get(0);
    }

    private static List<Object> findLaunchers(Class clazz) throws BeanCreationException {
        return BeanFactory.getInstance().getLaunchBeans().stream()
            .filter(bean ->
                bean.getClass().getAnnotation(Launcher.class).launchType()
                    .equals(((Launcher) clazz.getAnnotation(Launcher.class)).launchType()))
            .collect(Collectors.toList());
    }

    private static void instantiateBeans(Class clazz) throws BeanCreationException {
        String scanPackage = getScanPackageAttribute(clazz);
        BeanFactory.getInstance().init(scanPackage);
    }

    private static String getScanPackageAttribute(Class clazz) throws BeanCreationException {
        if (clazz.isAnnotationPresent(ScanPackage.class)) {
            ScanPackage scanPackage = (ScanPackage) clazz.getAnnotation(ScanPackage.class);
            return scanPackage.scanPackage().isBlank() ? clazz.getPackageName() : scanPackage.scanPackage();
        }
        throw new BeanCreationException(ErrorMessage.CANNOT_SCAN_PACKAGE.getValue());
    }
}
