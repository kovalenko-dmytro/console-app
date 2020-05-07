package com.kovalenko.application.runner.factory;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.application.runner.constant.RunnerType;
import com.kovalenko.application.runner.factory.console.ConsoleCommandProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandProviderFactoryTest {

    private static MessageSource messageSource = SystemMessageSource.getInstance();

    @Test
    void getImplementedProvider() throws ApplicationException {
        CommandProvider actual = CommandProviderFactory.getProvider(RunnerType.CONSOLE);

        assertNotNull(actual);
        assertEquals(ConsoleCommandProvider.class, actual.getClass());
    }

    @Test
    void whenScriptProviderNotImplementedShouldThrown() {
        Exception exception = assertThrows(ApplicationException.class, () -> CommandProviderFactory.getProvider(RunnerType.SCRIPT));
        assertEquals(messageSource.getMessage("error.command.provider.not.implemented", RunnerType.SCRIPT.getValue()), exception.getMessage());
    }

    @Test
    void whenXmlProviderNotImplementedShouldThrown() {
        Exception exception = assertThrows(ApplicationException.class, () -> CommandProviderFactory.getProvider(RunnerType.XML));
        assertEquals(messageSource.getMessage("error.command.provider.not.implemented", RunnerType.XML.getValue()), exception.getMessage());
    }
}