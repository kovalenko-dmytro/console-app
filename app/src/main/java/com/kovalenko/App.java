package com.kovalenko;

import com.kovalenko.application.Application;
import com.kovalenko.ioc.annotation.ConsoleApplication;

@ConsoleApplication
public class App {

    public static void main( String[] args ) {
        Application.launch(App.class, args);
    }
}
