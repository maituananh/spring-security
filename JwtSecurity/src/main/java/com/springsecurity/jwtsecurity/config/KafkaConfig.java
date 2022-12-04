package com.springsecurity.jwtsecurity.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    public static final String KEY_NOTIFICATION = "Notification";
    public static final String KEY_MESSAGE = "Message";

    @Bean
    public NewTopic notification() {
        return new NewTopic(KEY_NOTIFICATION, 2, (short) 1);
    }

    @Bean
    public NewTopic message() {
        return new NewTopic(KEY_MESSAGE, 1, (short) 1);
    }
}
