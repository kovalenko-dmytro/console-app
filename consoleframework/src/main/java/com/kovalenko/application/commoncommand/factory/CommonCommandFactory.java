package com.kovalenko.application.commoncommand.factory;

import com.kovalenko.application.commoncommand.CommonCommand;
import com.kovalenko.application.commoncommand.CommonCommandType;
import com.kovalenko.application.commoncommand.info.controller.ApiInfoCommand;

import java.util.Optional;

public class CommonCommandFactory {

    private CommonCommandFactory() {
    }

    public static Optional<CommonCommand> getCommand(CommonCommandType type) {
        switch (type) {
            case INFO:
                return Optional.of(new ApiInfoCommand());
            case UNDEFINED:
            default:
                return Optional.empty();
        }
    }
}
