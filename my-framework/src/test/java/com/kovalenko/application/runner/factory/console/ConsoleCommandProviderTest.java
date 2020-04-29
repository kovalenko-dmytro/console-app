package com.kovalenko.application.runner.factory.console;

import com.kovalenko.application.input.RequestReader;
import com.kovalenko.application.input.console.ConsoleRequestReader;
import com.kovalenko.application.input.constant.InputConstant;
import com.kovalenko.application.runner.constant.RunnerConstant;
import com.kovalenko.application.runner.factory.CommandProvider;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsoleCommandProviderTest {

    private static final String TEST_COMMAND = "test command";
    private RequestReader<String> requestReader = new ConsoleRequestReader();
    private InputStream stdin = System.in;
    private PrintStream stdout = System.out;

    @Test
    void getConsoleCommand() {
        try {
            System.setIn(new ByteArrayInputStream(TEST_COMMAND.getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            requestReader.setScanner(new Scanner(System.in));
            CommandProvider consoleCommandProvider = new ConsoleCommandProvider(requestReader);

            String actual = consoleCommandProvider.nextCommand();

            assertNotNull(actual);
            assertEquals(TEST_COMMAND, actual);
            assertEquals(getExpectedConsoleOutput(), outContent.toString());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

    private String getExpectedConsoleOutput() {
        return Stream
            .of(RunnerConstant.PLEASE_ENTER_REQUEST_COMMAND.getValue(),
                RunnerConstant.INFO_TO_VIEW_AVAILABLE_API_INFO.getValue(),
                RunnerConstant.EXIT_TO_EXIT_FROM_PROGRAM.getValue(),
                InputConstant.NEW_COMMAND_START.getValue())
            .collect(Collectors.joining(System.lineSeparator()));
    }
}