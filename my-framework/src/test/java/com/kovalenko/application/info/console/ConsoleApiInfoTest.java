package com.kovalenko.application.info.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.info.ApiInfo;
import com.kovalenko.application.info.entity.Info;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleApiInfoTest {

    @BeforeAll
    static void setAll() throws BeanCreationException {
        BeanFactory.getInstance().init("bean");
    }

    @Test
    void getInfo() throws ApplicationException, BeanCreationException {
        ApiInfo apiInfo = new ConsoleApiInfo();
        List<Info> actual = apiInfo.getInfo();

        assertTrue(actual.size() > 0);
    }
}