package com.kovalenko.application.message;

public interface MessageSource {
    String getMessage(String propertyKey, Object ... params);
    void setLocale(String languageTag);
}
