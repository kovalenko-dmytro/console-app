package com.kovalenko.application;

import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeAll;
import test.constant.TestConstant;

public abstract class BaseTest {

    @BeforeAll
    static void setAll() throws BeanCreationException {
        BeanFactory.getInstance().init(TestConstant.COMMON_PACKAGE_FOR_TEST.getValue());
    }
}
