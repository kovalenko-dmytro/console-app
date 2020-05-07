package com.kovalenko.application.invoke.console;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.console.ConsoleControllerResolver;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConsoleControllerMethodInvokerTest extends BaseTest {

    private static final String TEST_VOID_COMMAND = "test -param1 value1 -param2 value2 -param3 value3";
    private static final String TEST_RETURNED_OBJECT_COMMAND = "test view -param1 value1 -param2 value2 -param3 value3";
    private RequestPathMatchResult requestPathMatchResult;
    private ConsoleRequest consoleRequest;

    private RequestParser<ConsoleRequest> parser = new ConsoleRequestParser();
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver = new ConsoleControllerResolver();
    private Invoker<RequestPathMatchResult, ConsoleRequest> invoker = new ConsoleControllerMethodInvoker();

    @Test
    void invokeVoidControllerMethod() throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(TEST_VOID_COMMAND);
        requestPathMatchResult = resolver.resolve(consoleRequest);
        Object actual = invoker.invoke(requestPathMatchResult, consoleRequest);

        assertNull(actual);
    }

    @Test
    void invokeReturnedObjectControllerMethod() throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(TEST_RETURNED_OBJECT_COMMAND);
        requestPathMatchResult = resolver.resolve(consoleRequest);
        Object actual = invoker.invoke(requestPathMatchResult, consoleRequest);

        assertNotNull(actual);
    }
}