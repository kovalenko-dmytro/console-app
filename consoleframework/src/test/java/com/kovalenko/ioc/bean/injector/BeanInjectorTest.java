package com.kovalenko.ioc.bean.injector;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.ioc.bean.scanner.BeanScanner;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.constant.TestConstant;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BeanInjectorTest {

    private static final String TEST_PACKAGE_QUALIFIER_DID_NOT_FOUND_DI = "test.di.superclassfielddi.dinotfound";
    private static final String TEST_PACKAGE_QUALIFIER_DID_NOT_DEFINED = "test.di.superclassfielddi.notqualifier";
    private static final String TEST_PACKAGE_QUALIFIER_NOT_EXISTS = "test.di.superclassfielddi.qualifiernotexists";
    private static final String TEST_PACKAGE_DI_DID_NOT_FOUND = "test.di.fielddi";
    private static final String INVALID_QUALIFIER = "foo.qualifier";
    private static final String NOT_FOUND_DEPENDENCIES_SERVICE = "testDINotFoundDependenciesService";
    private static final String QUALIFIER_NOT_EXISTS_SERVICE_NAME = "testDIQualifierNotExistsService";

    private Map<String, Object> beans = new HashMap<>();
    private BeanScanner beanScanner = new BeanScanner();
    private BeanInjector beanInjector = new BeanInjector();
    private MessageSource messageSource = SystemMessageSource.getInstance();

    @BeforeEach
    void setUp() {
        beans.clear();
    }

    @Test
    void whenSuperClassFieldTypeDependenciesNotFoundShouldThrown() throws BeanCreationException {
        beanScanner.scanPackage(TEST_PACKAGE_QUALIFIER_DID_NOT_FOUND_DI, beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.cannot.find.dependency", NOT_FOUND_DEPENDENCIES_SERVICE), exception.getMessage());
    }

    @Test
    void whenSuperClassFieldTypeDependencyNotHaveQualifierShouldThrown() throws BeanCreationException {
        beanScanner.scanPackage(TEST_PACKAGE_QUALIFIER_DID_NOT_DEFINED, beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.qualifier.not.defined"), exception.getMessage());
    }

    @Test
    void whenSuperClassFieldTypeQualifierNotExistsShouldThrown() throws BeanCreationException {
        beanScanner.scanPackage(TEST_PACKAGE_QUALIFIER_NOT_EXISTS, beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.cannot.find.qualifier", INVALID_QUALIFIER, QUALIFIER_NOT_EXISTS_SERVICE_NAME), exception.getMessage());
    }

    @Test
    void whenFieldTypeDependenciesNotFoundShouldThrown() throws BeanCreationException {
        beanScanner.scanPackage(TEST_PACKAGE_DI_DID_NOT_FOUND, beans);

        Exception exception = assertThrows(BeanCreationException.class, () -> beanInjector.injectDependencies(beans));
        assertEquals(messageSource.getMessage("error.cannot.find.dependency", NOT_FOUND_DEPENDENCIES_SERVICE), exception.getMessage());
    }

    @Test
    void injectDependencies() throws BeanCreationException {
        beanScanner.scanPackage(TestConstant.COMMON_PACKAGE_FOR_TEST.getValue(), beans);
        assertDoesNotThrow(() -> beanInjector.injectDependencies(beans));
    }
}