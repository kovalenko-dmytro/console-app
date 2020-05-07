package com.kovalenko.application.configuration.console;

import com.kovalenko.application.configuration.AppConfiguration;
import com.kovalenko.application.configuration.AppConfigurationInitializer;
import com.kovalenko.application.configuration.constant.ConfigurationConstant;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleAppConfigurationInitializerTest {

    private static final Path CUSTOM_PROPERTIES_PATH = Paths.get("src", "test", "resources", "custom_application.properties");
    private static final String DEFAULT_TEST_CONFIG_PROPERTY_VALUE = "default test config property value";
    private static final String CUSTOM_TEST_CONFIG_PROPERTY_VALUE = "custom test config property value";

    private AppConfigurationInitializer configurationInitializer = new ConsoleAppConfigurationInitializer();
    private MessageSource messageSource = SystemMessageSource.getInstance();
    private Map<String, String> params = new HashMap<>();

    @BeforeEach
    void setUp() {
        params.clear();
    }

    @Test
    void whenPathToConfigPropertiesIsFailThenThrow() {
        params.put(ConfigurationConstant.CONFIG_KEY.getValue(), "failPath");
        Exception exception = assertThrows(ApplicationException.class, () -> configurationInitializer.initAppConfiguration(params));
        assertEquals(messageSource.getMessage("error.cannot.configure.app.properties"), exception.getMessage());
    }

    @Test
    void initDefaultAppConfiguration() throws ApplicationException {
        configurationInitializer.initAppConfiguration(params);

        Properties actual = AppConfiguration.getInstance().getProperties();

        assertNotNull(actual);
        assertEquals(DEFAULT_TEST_CONFIG_PROPERTY_VALUE, actual.getProperty("default.test.config.property.key"));
    }

    @Test
    void initCustomAppConfiguration() throws ApplicationException {
        params.put(ConfigurationConstant.CONFIG_KEY.getValue(), CUSTOM_PROPERTIES_PATH.toString());
        configurationInitializer.initAppConfiguration(params);

        Properties actual = AppConfiguration.getInstance().getProperties();

        assertNotNull(actual);
        assertEquals(CUSTOM_TEST_CONFIG_PROPERTY_VALUE, actual.getProperty("custom.test.config.property.key"));
    }
}