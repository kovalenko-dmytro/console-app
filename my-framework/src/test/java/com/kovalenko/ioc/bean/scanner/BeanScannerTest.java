package com.kovalenko.ioc.bean.scanner;

import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BeanScannerTest {

    @Test
    void whenBeanClassesExistShouldReturnNotEmptyContainer() throws BeanCreationException {
        BeanScanner beanScanner = new BeanScanner();
        Map<String, Object> actual = new HashMap<>();
        beanScanner.scanPackage("test", actual);

        assertFalse(actual.isEmpty());
    }

    @Test
    void whenBeanClassesNotExistShouldReturnEmptyContainer() throws BeanCreationException {
        BeanScanner beanScanner = new BeanScanner();
        Map<String, Object> actual = new HashMap<>();
        beanScanner.scanPackage("another", actual);

        assertTrue(actual.isEmpty());
    }
}