package com.anhmt.microservices.kafka.topic;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic topic1() {
        return new NewTopic("product", 3, (short) 1);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic("product-1", 2, (short) 1);
    }
}
