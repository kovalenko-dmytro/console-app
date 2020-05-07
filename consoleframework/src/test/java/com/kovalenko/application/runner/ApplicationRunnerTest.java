package com.kovalenko.application.runner;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.commoncommand.CommonCommandType;
import com.kovalenko.application.exception.ApplicationException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

class ApplicationRunnerTest extends BaseTest {

    private static final Path FILE_PATH = Paths.get("src", "test", "resources", "testFile.txt");
    private static final String TEST_COMMAND = "test -param1 value1 -param2 value2 -param3 ".concat(FILE_PATH.toString());
    private static final String EXIT = CommonCommandType.EXIT.getValue();

    private InputStream stdin = System.in;
    private PrintStream stdout = System.out;

    @Test
    void runApplication() throws ApplicationException {
        try {
            System.setIn(new ByteArrayInputStream(TEST_COMMAND.concat(System.lineSeparator()).concat(EXIT).getBytes()));
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            ApplicationRunner.getInstance().run(new HashMap<>());
        } finally {
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }
}