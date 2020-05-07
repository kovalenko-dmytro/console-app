package com.kovalenko.application.resolve.console;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.annotation.PathVariable;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleControllerResolverTest extends BaseTest {

    private static final String VALID_TEST_COMMAND = "test -param1 value1 -param2 value2 -param3 value3";
    private static final String DUPLICATE_PATH_VAR_NAMES_TEST_COMMAND = "test -param1 value1 -param2 value2";
    private static final String INVALID_TEST_COMMAND = "invalid -param1 value1 -param2 value2 -param3 value3";
    private static final String INVALID_TEST_COMMAND_REQUEST_PATH = "invalid {-param1} {-param2} {-param3}";
    private static final String CHECK_PATH_VAR_TEST_COMMAND = "check path var -param value";
    private static final String CHECK_PATH_VAR_REQUEST_PARAM = "-param";
    private static final String CHECK_PATH_VAR_REQUEST_PATH = "check path var {-param}";

    private ConsoleRequest consoleRequest;

    private RequestParser<ConsoleRequest> parser = new ConsoleRequestParser();
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver = new ConsoleControllerResolver();
    private MessageSource messageSource = SystemMessageSource.getInstance();

    @Test
    void resolveControllerMethod() throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(VALID_TEST_COMMAND);

        assertDoesNotThrow(() -> resolver.resolve(consoleRequest));
        RequestPathMatchResult actual = resolver.resolve(consoleRequest);

        assertNotNull(actual);
        assertNotNull(actual.getController());
        assertEquals("test.common.controller.TestController", actual.getController().getClass().getCanonicalName());
        assertNotNull(actual.getRequestPathMethod());
        assertNotNull(actual.getParameterTypes());
        assertNotEquals(0, actual.getParameterTypes().length);
    }

    @Test
    void whenInvalidRequestPathShouldThrown() throws ApplicationException {
        consoleRequest = parser.parse(INVALID_TEST_COMMAND);
        Exception exception = assertThrows(ApplicationException.class, () -> resolver.resolve(consoleRequest));
        assertEquals(messageSource.getMessage("error.cannot.resolve.request.path", INVALID_TEST_COMMAND_REQUEST_PATH), exception.getMessage());
    }

    @Test
    void whenPathVariableNotExistShouldThrown() throws ApplicationException {
        consoleRequest = parser.parse(CHECK_PATH_VAR_TEST_COMMAND);
        Exception exception = assertThrows(ApplicationException.class, () -> resolver.resolve(consoleRequest));
        assertEquals(messageSource.getMessage("error.cannot.resolve.path.variable", CHECK_PATH_VAR_REQUEST_PARAM, CHECK_PATH_VAR_REQUEST_PATH), exception.getMessage());
    }

    @Test
    void whenDuplicatePathVarNamesShouldThrown() throws ApplicationException {
        consoleRequest = parser.parse(DUPLICATE_PATH_VAR_NAMES_TEST_COMMAND);
        Exception exception = assertThrows(ApplicationException.class, () -> resolver.resolve(consoleRequest));
        assertEquals(messageSource.getMessage("error.cannot.duplicate.path.var.names", PathVariable.class.getSimpleName()), exception.getMessage());
    }
}