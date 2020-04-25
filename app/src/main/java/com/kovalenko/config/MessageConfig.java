package com.kovalenko.config;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.CommonMessageSource;
import com.kovalenko.ioc.annotation.Configuration;
import com.kovalenko.ioc.bean.factory.annotation.Bean;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        return new CommonMessageSource("messages", Locale.ENGLISH);
    }
}
