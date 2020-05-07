package com.kovalenko.application.configuration.console;

import com.kovalenko.application.configuration.ArgumentsParser;
import com.kovalenko.application.configuration.constant.ConfigurationConstant;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleArgumentsParser implements ArgumentsParser {

    @Override
    public Map<String, String> parse(String... args) {
        return Arrays.stream(args)
            .filter(arg -> arg.startsWith(ConfigurationConstant.KEY_PREFIX.getValue()))
            .map(entry -> entry.split(ConfigurationConstant.ARG_ASSIGN.getValue()))
            .collect(Collectors.toMap(entry -> entry[0].trim(), entry -> entry[1].trim()));
    }
}
