package com.kovalenko.application.runner.factory;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.application.runner.factory.console.ConsoleCommandProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandProviderFactoryTest {

    private static final String CONSOLE_COMMAND_SOURCE = "console";
    private static final String NOT_IMPLEMENTED_COMMAND_SOURCE = "-script";
    private static final String UNDEFINED_COMMAND_SOURCE = "-notImplementedSource";
    private static MessageSource messageSource = SystemMessageSource.getInstance();

    @Test
    void getImplementedProvider() throws ApplicationException {
        CommandProvider actual = CommandProviderFactory.getProvider(CONSOLE_COMMAND_SOURCE);

        assertNotNull(actual);
        assertEquals(ConsoleCommandProvider.class, actual.getClass());
    }

    @Test
    void whenUndefinedProviderShouldThrown() {
        Exception exception = assertThrows(ApplicationException.class, () -> CommandProviderFactory.getProvider(UNDEFINED_COMMAND_SOURCE));
        assertEquals(messageSource.getMessage("error.command.provider.undefined", UNDEFINED_COMMAND_SOURCE), exception.getMessage());
    }

    @Test
    void whenNotImplementedProviderShouldThrown() {
        Exception exception = assertThrows(ApplicationException.class, () -> CommandProviderFactory.getProvider(NOT_IMPLEMENTED_COMMAND_SOURCE));
        assertEquals(messageSource.getMessage("error.command.provider.not.implemented", NOT_IMPLEMENTED_COMMAND_SOURCE), exception.getMessage());
    }
}