package com.kovalenko.application.invoke.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.console.ConsoleControllerResolver;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.ioc.bean.factory.BeanFactory;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ConsoleControllerMethodInvokerTest {

    private static final String TEST_COMMAND = "test -param1 value1 -param2 value2 -param3 value3";
    private RequestPathMatchResult requestPathMatchResult;
    private ConsoleRequest consoleRequest;

    private RequestParser<ConsoleRequest> parser = new ConsoleRequestParser();
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver = new ConsoleControllerResolver();
    private Invoker<RequestPathMatchResult, ConsoleRequest> invoker = new ConsoleControllerMethodInvoker();

    @BeforeAll
    static void setAll() throws BeanCreationException {
        BeanFactory.getInstance().init("bean");
    }

    @BeforeEach
    void setUp() throws BeanCreationException, ApplicationException {
        consoleRequest = parser.parse(TEST_COMMAND);
        requestPathMatchResult = resolver.resolve(consoleRequest);
    }

    @Test
    void invokeControllerMethod() {
        assertDoesNotThrow(() -> invoker.invoke(requestPathMatchResult, consoleRequest));
    }
}