package com.kovalenko.application.input;

import java.util.Scanner;

public interface RequestReader<T> {
    T read();
    default void setScanner(Scanner scanner) {}
}
