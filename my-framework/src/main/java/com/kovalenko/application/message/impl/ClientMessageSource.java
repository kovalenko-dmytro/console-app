package com.kovalenko.application.message.impl;

import com.kovalenko.application.message.MessageSource;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ClientMessageSource implements MessageSource {

    @Override
    public String getMessage(String propertyKey, Object... params) {
        return propertyKey + " = " + Arrays.stream(params).map(param -> (String)param).collect(Collectors.joining(", "));
    }
}
