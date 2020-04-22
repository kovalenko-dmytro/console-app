package com.kovalenko.application.run;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.run.console.ConsoleRunner;
import com.kovalenko.ioc.constant.ErrorMessage;
import com.kovalenko.ioc.constant.LaunchType;

public class RunnerFactory {

    public static Runner getRunner(LaunchType type) throws ApplicationException {
        switch (type) {
            case CONSOLE:
                return new ConsoleRunner();

            default:
                throw new ApplicationException(ErrorMessage.CANNOT_RESOLVE_LAUNCH_TYPE.getValue());
        }
    }
}
