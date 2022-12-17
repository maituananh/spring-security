package com.anhmt.microservices.kafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class KafkaSendMessage {
    private final KafkaTemplate kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("product", msg);
    }
}
