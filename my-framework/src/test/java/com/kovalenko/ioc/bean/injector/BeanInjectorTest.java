package com.kovalenko.ioc.bean.injector;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.ioc.bean.scanner.BeanScanner;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BeanInjectorTest {

    private BeanScanner beanScanner = new BeanScanner();
    private BeanInjector beanInjector = new BeanInjector();
    private MessageSource messageSource = SystemMessageSource.getInstance();

    @Test
    void whenSuperClassFieldTypeDependenciesNotFoundShouldThrown() throws BeanCreationException {
        Map<String, Object> beans = new HashMap<>();
        beanScanner.scanPackage("test.di.superclassfielddi.dinotfound", beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.cannot.find.dependency", "testDINotFoundDependenciesService"), exception.getMessage());
    }

    @Test
    void whenSuperClassFieldTypeDependencyNotHaveQualifierShouldThrown() throws BeanCreationException {
        Map<String, Object> beans = new HashMap<>();
        beanScanner.scanPackage("test.di.superclassfielddi.notqualifier", beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.qualifier.not.defined"), exception.getMessage());
    }

    @Test
    void whenSuperClassFieldTypeQualifierNotFoundShouldThrown() throws BeanCreationException {
        Map<String, Object> beans = new HashMap<>();
        beanScanner.scanPackage("test.di.superclassfielddi.qualifiernotexists", beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.cannot.find.qualifier", "foo.qualifier", "testDIQualifierNotExistsService"), exception.getMessage());
    }

    @Test
    void whenFieldTypeDependenciesNotFoundShouldThrown() throws BeanCreationException {
        Map<String, Object> beans = new HashMap<>();
        beanScanner.scanPackage("test.di.fielddi", beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.cannot.find.dependency", "testDINotFoundDependenciesService"), exception.getMessage());
    }

    @Test
    void injectDependencies() throws BeanCreationException {
        Map<String, Object> beans = new HashMap<>();
        beanScanner.scanPackage("test.common", beans);
        assertDoesNotThrow(() -> beanInjector.injectDependencies(beans));
    }
}