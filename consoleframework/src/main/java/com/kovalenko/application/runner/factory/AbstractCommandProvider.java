package com.kovalenko.application.runner.factory;

import com.kovalenko.application.input.RequestReader;

public abstract class AbstractCommandProvider implements CommandProvider {

    private RequestReader<String> reader;

    public AbstractCommandProvider(RequestReader<String> reader) {
        this.reader = reader;
    }

    @Override
    public String nextCommand() {
        return reader.read();
    }
}
