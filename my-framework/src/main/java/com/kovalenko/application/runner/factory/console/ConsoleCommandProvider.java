package com.kovalenko.application.runner.factory.console;

import com.kovalenko.application.input.RequestReader;
import com.kovalenko.application.input.console.ConsoleRequestReader;
import com.kovalenko.application.runner.constant.RunnerConstant;
import com.kovalenko.application.runner.factory.CommandProvider;

public class ConsoleCommandProvider implements CommandProvider {

    private RequestReader<String> reader;

    public ConsoleCommandProvider() {
        reader = new ConsoleRequestReader();
        printPreview();
    }

    @Override
    public String nextCommand() {
        return reader.read();
    }

    private void printPreview() {
        System.out.println(RunnerConstant.PLEASE_ENTER_REQUEST_COMMAND.getValue());
        System.out.println(RunnerConstant.INFO_TO_VIEW_AVAILABLE_API_INFO.getValue());
        System.out.println(RunnerConstant.EXIT_TO_EXIT_FROM_PROGRAM.getValue());
    }
}
