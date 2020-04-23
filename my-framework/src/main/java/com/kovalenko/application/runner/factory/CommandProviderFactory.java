package com.kovalenko.application.runner.factory;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.runner.constant.RunnerType;
import com.kovalenko.application.runner.factory.console.ConsoleCommandProvider;
import com.kovalenko.ioc.constant.ErrorMessage;

import java.text.MessageFormat;

public class CommandProviderFactory {

    public static CommandProvider getProvider(String name) throws ApplicationException {
        RunnerType type = RunnerType.getType(name);
        switch (type) {
            case CONSOLE:
                return new ConsoleCommandProvider();

            default:
                throw new ApplicationException(MessageFormat.format(ErrorMessage.RUNNER_TYPE_NOT_DEFINED.getValue(), type));
        }
    }
}
