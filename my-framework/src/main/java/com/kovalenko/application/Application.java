package com.kovalenko.application;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.runner.ApplicationRunner;
import com.kovalenko.ioc.annotation.ScanPackage;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.constant.ErrorMessage;
import com.kovalenko.ioc.exception.BeanCreationException;

public class Application {

    private Application(){}

    public static void launch(Class clazz, String... args) {
        try {
            instantiateBeans(clazz);
            ApplicationRunner.getInstance().run(args);
        } catch (ApplicationException | BeanCreationException e) {
            System.out.println(e.getMessage());
        }
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