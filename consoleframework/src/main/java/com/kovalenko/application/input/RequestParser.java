package com.kovalenko.application.input;

import com.kovalenko.application.exception.ApplicationException;

public interface RequestParser<T> {
    T parse(String input) throws ApplicationException;
}
