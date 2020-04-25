package com.kovalenko.config;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.ClientMessageSource;
import com.kovalenko.ioc.annotation.Configuration;
import com.kovalenko.ioc.bean.factory.annotation.Bean;

import java.util.Locale;

@Configuration
public class MessageConfig {

    @Bean
    public MessageSource messageSource() {
        return new ClientMessageSource("messages", Locale.ENGLISH);
    }
}
