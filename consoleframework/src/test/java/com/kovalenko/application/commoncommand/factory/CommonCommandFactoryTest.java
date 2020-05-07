package com.kovalenko.application.commoncommand.factory;

import com.kovalenko.application.commoncommand.CommonCommand;
import com.kovalenko.application.commoncommand.CommonCommandType;
import com.kovalenko.application.commoncommand.info.controller.ApiInfoCommand;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommonCommandFactoryTest {

    private static final String UNDEFINED_COMMAND = "test undefined command";

    @Test
    void whenCommandExistsThenReturnCommandImpl() {
        Optional<CommonCommand> actual = CommonCommandFactory.getCommand(CommonCommandType.INFO);

        assertTrue(actual.isPresent());
        assertEquals(actual.get().getClass(), ApiInfoCommand.class);
    }

    @Test
    void whenCommandUndefinedThenReturnEmpty() {
        CommonCommandType commonCommandType = CommonCommandType.findCommonCommandType(UNDEFINED_COMMAND);
        Optional<CommonCommand> actual = CommonCommandFactory.getCommand(commonCommandType);

        assertTrue(actual.isEmpty());
    }
}