package com.anhmt.microservices.producer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaReceiveMessage {

    @KafkaListener(topics = "product", groupId = "product")
    public void listen(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }
}
