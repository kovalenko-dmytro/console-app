package com.kovalenko.application.validate.console;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.console.ConsoleControllerResolver;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.application.validate.Validator;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerMethodArgsValidatorTest extends BaseTest {

    private static final String NOT_NULL_PARAM_TEST_COMMAND = "test not null -param";
    private static final String NOT_EMPTY_PARAM_TEST_COMMAND = "test not empty -param ";
    private static final String NOT_BLANK_PARAM_TEST_COMMAND = "test not blank -param   ";
    private static final String FILE_PATH_PARAM_TEST_COMMAND = "test not file path -param fail/path/to/file";

    private ConsoleRequest consoleRequest;
    private RequestPathMatchResult pathMatchResult;

    private RequestParser<ConsoleRequest> parser = new ConsoleRequestParser();
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver = new ConsoleControllerResolver();
    private Validator<RequestPathMatchResult, ConsoleRequest> validator = new ControllerMethodArgsValidator();

    @BeforeEach
    void setUp() {
        consoleRequest = null;
        pathMatchResult = null;
    }

    @Test
    void whenParamIsNullShouldThrown() throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(NOT_NULL_PARAM_TEST_COMMAND);
        pathMatchResult = resolver.resolve(consoleRequest);
        Exception exception = assertThrows(ApplicationException.class, () -> validator.validate(pathMatchResult, consoleRequest));
        assertEquals("ERROR:: <-param> path variable cannot be null", exception.getMessage());
    }

    @Test
    void whenParamIsEmptyShouldThrown() throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(NOT_EMPTY_PARAM_TEST_COMMAND);
        pathMatchResult = resolver.resolve(consoleRequest);
        Exception exception = assertThrows(ApplicationException.class, () -> validator.validate(pathMatchResult, consoleRequest));
        assertEquals("ERROR:: <-param> path variable cannot be empty", exception.getMessage());
    }

    @Test
    void whenParamIsBlankShouldThrown() throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(NOT_BLANK_PARAM_TEST_COMMAND);
        pathMatchResult = resolver.resolve(consoleRequest);
        Exception exception = assertThrows(ApplicationException.class, () -> validator.validate(pathMatchResult, consoleRequest));
        assertEquals("ERROR:: <-param> path variable cannot be blank", exception.getMessage());
    }

    @Test
    void whenParamIsNotFilePathShouldThrown() throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(FILE_PATH_PARAM_TEST_COMMAND);
        pathMatchResult = resolver.resolve(consoleRequest);
        Exception exception = assertThrows(ApplicationException.class, () -> validator.validate(pathMatchResult, consoleRequest));
        assertEquals("ERROR:: <-param> path variable must be valid file or directory path", exception.getMessage());
    }
}