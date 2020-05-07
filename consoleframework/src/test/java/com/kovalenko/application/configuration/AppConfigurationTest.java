package com.kovalenko.application.configuration;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigurationTest {

    private static final String TEST_PROPERTY_KEY = "key";
    private static final String TEST_PROPERTY_VALUE = "value";

    private Properties properties = new Properties();
    private AppConfiguration appConfiguration = AppConfiguration.getInstance();
    private MessageSource messageSource = SystemMessageSource.getInstance();

    @BeforeEach
    void setUp() {
        properties.clear();
        properties.setProperty(TEST_PROPERTY_KEY, TEST_PROPERTY_VALUE);
        appConfiguration.initProperties(properties);
    }

    @Test
    void initProperties() throws ApplicationException {
        assertNotNull(appConfiguration.getProperties());
    }

    @Test
    void whenPropertiesWerentInitThenShouldThrow() {
        appConfiguration.initProperties(null);
        Exception exception = assertThrows(ApplicationException.class, () -> appConfiguration.getProperties());
        assertEquals(messageSource.getMessage("error.app.config.properties.not.init"), exception.getMessage());
    }

    @Test
    void whenPropertiesAreInitThenShouldReturnProperties() throws ApplicationException {
        assertNotNull(appConfiguration.getProperties());
        assertFalse(appConfiguration.getProperties().isEmpty());
    }

    @Test
    void getProperty() throws ApplicationException {
        String actual = appConfiguration.getProperty(TEST_PROPERTY_KEY);

        assertNotNull(actual);
        assertEquals(TEST_PROPERTY_VALUE, actual);
    }
}