package com.kovalenko.application.message.impl;

import com.kovalenko.application.message.AbstractMessageSource;
import com.kovalenko.application.message.MessageSource;

public class SystemMessageSource extends AbstractMessageSource {

    private static final String SYSTEM_RESOURCE_PROPERTIES_PATH = "system_messages";

    private SystemMessageSource(String resourcePropertiesPath) {
        super(resourcePropertiesPath);
    }

    private static class SystemMessageSourceHolder {
        private static final SystemMessageSource instance = new SystemMessageSource(SYSTEM_RESOURCE_PROPERTIES_PATH);
    }

    public static MessageSource getInstance() {
        return SystemMessageSourceHolder.instance;
    }
}
