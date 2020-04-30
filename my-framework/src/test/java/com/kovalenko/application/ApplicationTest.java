package com.kovalenko.application;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.ioc.annotation.ConsoleApplication;
import org.junit.jupiter.api.Test;
import test.NotAnnotatedLaunchClass;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApplicationTest extends BaseTest {

    private PrintStream stdout = System.out;
    private MessageSource messageSource = SystemMessageSource.getInstance();

    @Test
    void whenLaunchClassIsNotAnnotatedShouldThrown() {
        try {
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            Application.launch(NotAnnotatedLaunchClass.class);
            String actual = outContent.toString().replace(System.lineSeparator(), "");
            assertEquals(messageSource.getMessage("error.annotation.wasnt.defined", ConsoleApplication.class), actual);
        } finally {
            System.setOut(stdout);
        }
    }
}