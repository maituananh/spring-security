package com.anhmt.microservices.bloc;

import com.anhmt.microservices.kafka.producer.KafkaSendMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductListBloc {

    private final KafkaSendMessage kafkaSendMessage;

    public void send() {
        kafkaSendMessage.sendMessage("alo");
    }
}
