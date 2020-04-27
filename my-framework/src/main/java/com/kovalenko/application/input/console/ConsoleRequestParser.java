package com.kovalenko.application.input.console;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.input.RequestParser;
import com.kovalenko.application.input.constant.InputConstant;
import com.kovalenko.application.input.entity.ConsoleRequest;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleRequestParser implements RequestParser<ConsoleRequest> {

    private MessageSource messageSource = SystemMessageSource.getInstance();

    @Override
    public ConsoleRequest parse(String input) throws ApplicationException {
        ConsoleRequest request = new ConsoleRequest();
        request.setRequestPath(getRequestPath(input));
        request.setRequestParameters(getRequestParameters(input));
        return request;
    }

    private String getRequestPath(String input) throws ApplicationException {
        Matcher matcher = Pattern.compile(InputConstant.REQUEST_PATH_REGEX.getValue(), Pattern.CASE_INSENSITIVE).matcher(input);
        if (matcher.find()) {
            return matcher.group().trim();
        }
        throw new ApplicationException(messageSource.getMessage("error.cannot.parse.request.path"));
    }

    private Map<String, String> getRequestParameters(String input) {
        Map<String, String> result = new LinkedHashMap<>();
        Matcher matcher = Pattern.compile(InputConstant.REQUEST_PARAMS_REGEX.getValue(), Pattern.CASE_INSENSITIVE).matcher(input);
        while (matcher.find()) {
            String paramName = matcher.group(1);
            String paramValue = matcher.group(4);
            if (Objects.isNull(paramValue)) {
                paramValue = matcher.group(5);
            }
            result.put(paramName, paramValue);
        }
        return result;
    }
}
