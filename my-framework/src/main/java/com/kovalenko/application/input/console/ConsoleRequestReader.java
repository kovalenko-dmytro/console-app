package com.kovalenko.application.input.console;

import com.kovalenko.application.input.RequestReader;
import com.kovalenko.application.input.constant.InputConstant;

import java.util.Scanner;

public class ConsoleRequestReader implements RequestReader<String> {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        System.out.print(InputConstant.NEW_COMMAND_START.getValue());
        return scanner.nextLine();
    }

    @Override
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
