package com.kovalenko.application.message;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class AbstractMessageSource implements MessageSource{

    private ResourceBundle resourceBundle;

    public AbstractMessageSource(String resourcePropertiesPath, Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourcePropertiesPath, locale);
    }

    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }
}
