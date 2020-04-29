package com.kovalenko.application.input.console;

import com.kovalenko.application.input.RequestReader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConsoleRequestReaderTest {

    private static final String TEST_COMMAND = "test command";
    private RequestReader<String> requestReader = new ConsoleRequestReader();

    @Test
    void readUserInput() {
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(TEST_COMMAND.getBytes()));
            requestReader.setScanner(new Scanner(System.in));

            String actual = requestReader.read();

            assertNotNull(actual);
            assertEquals(TEST_COMMAND, actual);
        } finally {
            System.setIn(stdin);
        }
    }
}