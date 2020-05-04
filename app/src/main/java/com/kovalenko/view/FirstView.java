package com.kovalenko.view;

import com.kovalenko.application.view.view.ConsoleView;

import java.util.List;

public class FirstView extends ConsoleView<List<String>> {

    @Override
    public void render() {
        getBody().forEach(System.out::println);
        System.out.println(getResponseStatus());
    }
}
