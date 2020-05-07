package com.kovalenko.application.commoncommand;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.console.ConsoleRequestParser;
import com.kovalenko.application.input.entity.ConsoleRequest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonCommandExecutorTest extends BaseTest {

    private static final String EXIST_COMMON_COMMAND = "info";
    private static final String UNDEFINED_COMMON_COMMAND = "any client input";

    private ConsoleRequest consoleRequest;
    private RequestParser<ConsoleRequest> parser = new ConsoleRequestParser();
    private CommonCommandExecutor commonCommandExecutor = new CommonCommandExecutor();
    private PrintStream stdout = System.out;

    @Test
    void whenExecuteExistingCommandThenRenderResult() throws ApplicationException {
        consoleRequest = parser.parse(EXIST_COMMON_COMMAND);
        try {
            ByteArrayOutputStream outContent = renderExecuteResult();
            assertFalse(outContent.toString().isEmpty());
        } finally {
            System.setOut(stdout);
        }
    }

    @Test
    void whenCommandNotExistsThenRenderEmpty() throws ApplicationException {
        consoleRequest = parser.parse(UNDEFINED_COMMON_COMMAND);
        try {
            ByteArrayOutputStream outContent = renderExecuteResult();
            assertTrue(outContent.toString().isEmpty());
        } finally {
            System.setOut(stdout);
        }
    }

    private ByteArrayOutputStream renderExecuteResult() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        commonCommandExecutor.execute(consoleRequest);
        return outContent;
    }
}