package com.kovalenko.application.configuration;

import com.kovalenko.application.exception.ApplicationException;

import java.util.Map;

public interface AppConfigurationInitializer {
    void initAppConfiguration(Map<String, String> inputParams) throws ApplicationException;
}
