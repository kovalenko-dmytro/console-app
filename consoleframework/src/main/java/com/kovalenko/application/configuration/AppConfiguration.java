package com.kovalenko.application.configuration;

import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;

import java.util.Objects;
import java.util.Properties;

public class AppConfiguration {

    private Properties properties;
    private MessageSource messageSource = SystemMessageSource.getInstance();

    private AppConfiguration() {}

    private static class AppConfigurationHolder {
        private static final AppConfiguration instance = new AppConfiguration();
    }

    public static AppConfiguration getInstance() {
        return AppConfigurationHolder.instance;
    }

    public void initProperties(Properties properties) {
        this.properties = properties;
    }

    public Properties getProperties() throws ApplicationException {
        checkInitProperties();
        return properties;
    }

    public String getProperty(String propertyName) throws ApplicationException {
        checkInitProperties();
        return properties.getProperty(propertyName);
    }

    private void checkInitProperties() throws ApplicationException {
        if (Objects.isNull(properties)) {
            throw new ApplicationException(messageSource.getMessage("error.app.config.properties.not.init"));
        }
    }
}
