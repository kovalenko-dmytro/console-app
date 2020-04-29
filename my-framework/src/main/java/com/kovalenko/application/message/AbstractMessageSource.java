package com.kovalenko.application.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class AbstractMessageSource implements MessageSource{

    private Locale locale = Locale.ENGLISH;
    private ResourceBundle resourceBundle;

    public AbstractMessageSource(String resourcePropertiesPath) {
        resourceBundle = ResourceBundle.getBundle(resourcePropertiesPath, locale);
    }

    @Override
    public String getMessage(String propertyKey, Object... params) {
        return MessageFormat.format(resourceBundle.getString(propertyKey), params);
    }

    @Override
    public void setLocale(String languageTag) {
        this.locale = Locale.forLanguageTag(languageTag);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setResourcePropertiesPath(String resourcePropertiesPath) {
        resourceBundle = ResourceBundle.getBundle(resourcePropertiesPath, locale);
    }
}
