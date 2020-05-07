package com.kovalenko.application.input.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleRequestParserTest {

    private static final String INVALID_TEST_INPUT = "-wrong input";
    private static final String VALID_TEST_INPUT = "test command -param1 value1 -param2 value2";
    private static final String TEST_COMMAND = "test command";
    private static final String DUPLICATE_PARAMS_TEST_INPUT = "test -param value1 -param value2";
    private static final Map<String, String> TEST_COMMAND_PARAMETERS = Map.of("-param1", "value1", "-param2", "value2");

    private InputStream stdin = System.in;
    private String input;

    private RequestParser<ConsoleRequest> requestParser = new ConsoleRequestParser();
    private MessageSource messageSource = SystemMessageSource.getInstance();

    @AfterEach
    void tearDown() {
        System.setIn(stdin);
    }

    @Test
    void parseValidUserCommand() throws ApplicationException {
        getUserInput(VALID_TEST_INPUT);

        ConsoleRequest actual = requestParser.parse(input);

        assertNotNull(actual.getRequestPath());
        assertNotNull(actual.getRequestParameters());
        assertEquals(TEST_COMMAND, actual.getRequestPath());
        actual.getRequestParameters().forEach((param, value) -> {
            assertEquals(TEST_COMMAND_PARAMETERS.get(param), value);
        });
    }

    @Test
    void whenInvalidUserCommandShouldThrown() {
        getUserInput(INVALID_TEST_INPUT);

        Exception exception = assertThrows(ApplicationException.class, () -> requestParser.parse(input));
        assertEquals(messageSource.getMessage("error.cannot.parse.request.path"), exception.getMessage());
    }

    @Test
    void whenDuplicateRequestPathParamsShouldThrown() {
        getUserInput(DUPLICATE_PARAMS_TEST_INPUT);

        Exception exception = assertThrows(ApplicationException.class, () -> requestParser.parse(input));
        assertEquals(messageSource.getMessage("error.cannot.duplicate.param.names"), exception.getMessage());
    }

    private void getUserInput(String command) {
        System.setIn(new ByteArrayInputStream(command.getBytes()));
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
    }
}