package test.common.config;

import com.kovalenko.application.message.MessageSource;
import com.kovalenko.application.message.impl.CommonMessageSource;
import com.kovalenko.ioc.annotation.Configuration;
import com.kovalenko.ioc.bean.factory.annotation.Bean;

@Configuration
public class TestConfiguration {

    @Bean
    public MessageSource messageSource() {
        return new CommonMessageSource("test_messages");
    }
}
