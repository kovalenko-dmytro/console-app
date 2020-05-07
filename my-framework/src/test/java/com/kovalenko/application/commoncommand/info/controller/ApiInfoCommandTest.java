package com.kovalenko.application.commoncommand.info.controller;

import com.kovalenko.application.BaseTest;
import com.kovalenko.application.commoncommand.CommonCommand;
import com.kovalenko.application.commoncommand.CommonCommandType;
import com.kovalenko.application.commoncommand.factory.CommonCommandFactory;
import com.kovalenko.application.view.view.ConsoleView;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiInfoCommandTest extends BaseTest {

    @Test
    void executeAndGetViewWithResults() {
        Optional<CommonCommand> actual = CommonCommandFactory.getCommand(CommonCommandType.INFO);

        assertTrue(actual.isPresent());
        ConsoleView actualView = actual.get().execute();
        assertNotNull(actualView);
        assertNotNull(actualView.getBody());
    }
}