package com.kovalenko.application;

import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    static void setAll() throws BeanCreationException {
        BeanFactory.getInstance().init("bean");
    }
}
