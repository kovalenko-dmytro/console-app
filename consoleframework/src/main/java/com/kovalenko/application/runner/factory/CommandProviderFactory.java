package com.kovalenko.application.runner.factory;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.console.ConsoleRequestReader;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import com.kovalenko.application.runner.constant.RunnerType;
import com.kovalenko.application.runner.factory.console.ConsoleCommandProvider;

public class CommandProviderFactory {

    private static MessageSource messageSource = SystemMessageSource.getInstance();

    public static CommandProvider getProvider(RunnerType runnerType) throws ApplicationException {
        switch (runnerType) {
            case SCRIPT:
            case XML:
                throw new ApplicationException(messageSource.getMessage("error.command.provider.not.implemented", runnerType.getValue()));
            case CONSOLE:
            default:
                return new ConsoleCommandProvider(new ConsoleRequestReader());
        }
    }
}
