package com.kovalenko.application.view.render;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.invoke.Invoker;
import com.kovalenko.application.invoke.console.ConsoleControllerMethodInvoker;
import com.kovalenko.application.resolve.Resolver;
import com.kovalenko.application.resolve.console.ConsoleControllerResolver;
import com.kovalenko.application.resolve.entity.RequestPathMatchResult;
import com.kovalenko.ioc.exception.BeanCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ViewRendererTest extends BaseTest {

    private static final String VOID_RETURN_COMMAND = "test void -param value";
    private static final String STRING_RETURN_COMMAND = "test string -param value";
    private static final String VIEW_RETURN_COMMAND = "test view -param1 value1 -param2 value2 -param3 value3";

    private static final String STRING_RETURNED_RESULT = "value";
    private static final String VIEW_RETURNED_RESULT = "OK\r\nno error message\r\n[value1, value2, value3]\r\n";

    private RequestPathMatchResult requestPathMatchResult;
    private ConsoleRequest consoleRequest;
    private Object invokeResult;

    private RequestParser<ConsoleRequest> parser = new ConsoleRequestParser();
    private Resolver<ConsoleRequest, RequestPathMatchResult> resolver = new ConsoleControllerResolver();
    private Invoker<RequestPathMatchResult, ConsoleRequest> invoker = new ConsoleControllerMethodInvoker();
    private Renderer renderer = new ViewRenderer();
    private PrintStream stdout = System.out;

    @BeforeEach
    void setUp() {
        requestPathMatchResult = null;
        consoleRequest = null;
        invokeResult = null;
    }

    @Test
    void whenVoidReturnShouldNothingToDo() throws BeanCreationException, ApplicationException {
        processRequest(VOID_RETURN_COMMAND);
        try {
            ByteArrayOutputStream outContent = renderInvokeResult();

            assertTrue(outContent.toString().isEmpty());
        } finally {
            System.setOut(stdout);
        }
    }

    @Test
    void whenStringReturnShouldRenderStringValue() throws BeanCreationException, ApplicationException {
        processRequest(STRING_RETURN_COMMAND);
        try {
            ByteArrayOutputStream outContent = renderInvokeResult();
            String actual = outContent.toString().replace(System.lineSeparator(), "");

            assertFalse(actual.isEmpty());
            assertEquals(STRING_RETURNED_RESULT, actual);
        } finally {
            System.setOut(stdout);
        }
    }

    @Test
    void whenViewReturnShouldRenderViewData() throws BeanCreationException, ApplicationException {
        processRequest(VIEW_RETURN_COMMAND);
        try {
            ByteArrayOutputStream outContent = renderInvokeResult();
            String actual = outContent.toString();

            assertFalse(actual.isEmpty());
            assertEquals(VIEW_RETURNED_RESULT, actual);
        } finally {
            System.setOut(stdout);
        }
    }

    private void processRequest(String request) throws ApplicationException, BeanCreationException {
        consoleRequest = parser.parse(request);
        requestPathMatchResult = resolver.resolve(consoleRequest);
        invokeResult = invoker.invoke(requestPathMatchResult, consoleRequest);
    }

    private ByteArrayOutputStream renderInvokeResult() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        renderer.render(invokeResult);
        return outContent;
    }
}