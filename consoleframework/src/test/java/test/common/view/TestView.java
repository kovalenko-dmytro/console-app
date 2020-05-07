package test.common.view;

import com.kovalenko.application.view.view.ConsoleView;

import java.util.List;

public class TestView extends ConsoleView<List<String>> {

    @Override
    public void render() {
        System.out.println(getResponseStatus());
        System.out.println(getErrorMessage());
        System.out.println(getBody());
    }
}
