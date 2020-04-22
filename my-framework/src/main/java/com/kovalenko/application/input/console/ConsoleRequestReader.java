package com.kovalenko.application.input.console;

import com.kovalenko.application.input.RequestReader;
import com.kovalenko.application.input.constant.InputConstant;

import java.util.Scanner;

public class ConsoleRequestReader implements RequestReader<String> {



    @Override
    public String read() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(InputConstant.NEW_COMMAND_START.getValue());
            String input = scanner.nextLine();
            if(!input.isBlank()) {
                return input;
            }
        }
    }
}
