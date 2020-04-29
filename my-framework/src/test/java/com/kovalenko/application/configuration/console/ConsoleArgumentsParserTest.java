package com.kovalenko.application.configuration.console;

import com.kovalenko.application.configuration.ArgumentsParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleArgumentsParserTest {

    private String[] args;

    @BeforeEach
    void setUp() {
        args = new String[]{
            "-config=path/to/config",
            "-script=path/to/script",
            "failArg=fail"
        };
    }

    @Test
    void parseProgramArguments() {
        ArgumentsParser parser = new ConsoleArgumentsParser();
        Map<String, String> actual = parser.parse(args);

        assertNotNull(actual);
        assertTrue(actual.size() > 0);
        assertEquals(2, actual.size());
    }
}