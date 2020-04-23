package com.kovalenko.application.configuration;

import com.kovalenko.application.exception.ApplicationException;

import java.util.Map;

public interface ArgumentsParser {
    Map<String, String> parse(String... args) throws ApplicationException;
}
