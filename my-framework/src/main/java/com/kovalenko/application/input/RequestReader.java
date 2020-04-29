package com.kovalenko.application.input;

import java.util.Scanner;

public interface RequestReader<T> {
    T read();
    void setScanner(Scanner scanner);
}
