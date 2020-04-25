package com.kovalenko.application.message.impl;

import com.kovalenko.application.message.AbstractMessageSource;

import java.text.MessageFormat;
import java.util.Locale;

public class CommonMessageSource extends AbstractMessageSource {

    public CommonMessageSource(String resourcePropertiesPath, Locale locale) {
        super(resourcePropertiesPath, locale);
    }

    @Override
    public String getMessage(String propertyKey, Object... params) {
        return MessageFormat.format(getResourceBundle().getString(propertyKey), params);
    }
}
