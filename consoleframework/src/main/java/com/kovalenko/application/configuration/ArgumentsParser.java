package com.kovalenko.application.configuration;

import java.util.Map;

public interface ArgumentsParser {
    Map<String, String> parse(String... args);
}
