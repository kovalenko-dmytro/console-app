package com.kovalenko.application.runner.factory.console;

import com.kovalenko.application.input.RequestReader;
import com.kovalenko.application.runner.constant.RunnerConstant;
import com.kovalenko.application.runner.factory.AbstractCommandProvider;

public class ConsoleCommandProvider extends AbstractCommandProvider {

    public ConsoleCommandProvider(RequestReader<String> reader) {
        super(reader);
        printPreview();
    }

    private void printPreview() {
        System.out.println(RunnerConstant.PLEASE_ENTER_REQUEST_COMMAND.getValue());
        System.out.println(RunnerConstant.INFO_TO_VIEW_AVAILABLE_API_INFO.getValue());
        System.out.println(RunnerConstant.EXIT_TO_EXIT_FROM_PROGRAM.getValue());
    }
}
