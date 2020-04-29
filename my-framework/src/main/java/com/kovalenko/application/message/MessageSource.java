package com.kovalenko.application.message;

import java.util.Locale;

public interface MessageSource {
    String getMessage(String propertyKey, Object ... params);
    void setLocale(String languageTag);
    Locale getLocale();
}
