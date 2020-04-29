package com.kovalenko.application.message;

import com.kovalenko.application.message.impl.CommonMessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AbstractMessageSourceTest {

    private static final String COMMON_RESOURCE_PROPERTIES_PATH = "messages";
    private static final String TEST_MESSAGE_PARAM = "test";
    private static final String SYSTEM_TEST_MESSAGE = "system test message: test";
    private static final String COMMON_TEST_MESSAGE = "test message: test";

    private MessageSource messageSource;

    @Test
    void getCommonMessage() {
        messageSource = new CommonMessageSource(COMMON_RESOURCE_PROPERTIES_PATH);

        String actual = messageSource.getMessage("test.message", TEST_MESSAGE_PARAM);

        assertNotNull(actual);
        assertEquals(COMMON_TEST_MESSAGE, actual);
    }

    @Test
    void getSystemMessage() {
        messageSource = SystemMessageSource.getInstance();

        String actual = messageSource.getMessage("system.test.message", TEST_MESSAGE_PARAM);

        assertNotNull(actual);
        assertEquals(SYSTEM_TEST_MESSAGE, actual);
    }

    @Test
    void setLocale() {
        messageSource = SystemMessageSource.getInstance();
        messageSource.setLocale("fr");

        Locale actual = messageSource.getLocale();

        assertEquals(Locale.FRENCH, actual);
    }
}