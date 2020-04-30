package com.kovalenko.ioc.bean.factory;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private MessageSource messageSource = SystemMessageSource.getInstance();

    @BeforeEach
    void setUp() {
        BeanFactory.getInstance().clearBeanContainer();
    }

    @Test
    void initBeans() throws BeanCreationException {
        BeanFactory.getInstance().init("test.common");

        Map<String, Object> actual = BeanFactory.getInstance().getBeans();

        assertFalse(actual.isEmpty());
    }

    @Test
    void whenBeansNotInitializedShouldThrown() {
        Exception exception = assertThrows(BeanCreationException.class, () -> BeanFactory.getInstance().getBeans());
        assertEquals(messageSource.getMessage("error.any.beans.not.found"), exception.getMessage());
    }

    @Test
    void whenBeansInitializedShouldReturnControllers() throws BeanCreationException {
        BeanFactory.getInstance().init("test.common");

        List<Object> actual = BeanFactory.getInstance().getControllers();

        assertFalse(actual.isEmpty());
    }
}