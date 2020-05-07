package com.kovalenko.application.commoncommand;

import com.kovalenko.application.view.view.ConsoleView;

public interface CommonCommand<T> {
    ConsoleView<T> execute();
}
