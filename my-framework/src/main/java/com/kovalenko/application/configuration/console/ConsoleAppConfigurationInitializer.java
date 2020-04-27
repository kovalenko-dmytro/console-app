package com.kovalenko.application.configuration.console;

import com.kovalenko.application.configuration.AppConfiguration;
import com.kovalenko.application.configuration.AppConfigurationInitializer;
import com.kovalenko.application.configuration.constant.ConfigurationConstant;
import com.kovalenko.application.exception.ApplicationException;
import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.SystemMessageSource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ConsoleAppConfigurationInitializer implements AppConfigurationInitializer {

    private MessageSource messageSource = SystemMessageSource.getInstance();

    @Override
    public void initAppConfiguration(Map<String, String> inputParams) throws ApplicationException {
        Properties properties = loadProperties(inputParams);
        AppConfiguration.getInstance().initProperties(properties);
    }

    private Properties loadProperties(Map<String, String> arguments) throws ApplicationException {
        Properties result = new Properties();
        InputStream in;
        try {
            in = arguments.containsKey(ConfigurationConstant.CONFIG_KEY.getValue())
                ? new FileInputStream(arguments.get(ConfigurationConstant.CONFIG_KEY.getValue()))
                : getClass().getResourceAsStream(ConfigurationConstant.CONFIGURATION_PROPERTIES_DEFAULT_FILE_PATH.getValue());
            result.load(in);
            return result;
        } catch (Exception e) {
            throw new ApplicationException(messageSource.getMessage("error.cannot.configure.app.properties"));
        }
    }
}
