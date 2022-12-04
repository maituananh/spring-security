package com.springsecurity.jwtsecurity.api;

import com.springsecurity.jwtsecurity.config.KafkaConfig;
import com.springsecurity.jwtsecurity.dto.request.KafkaReq;
import com.springsecurity.jwtsecurity.exception.ValidatorException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/kafkas")
@AllArgsConstructor
public class KafkaController extends BaseController {

    KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN') and hasAuthority('WRITE')")
    public ResponseEntity<?> store(@RequestBody @Valid final KafkaReq kafkaReq) throws ValidatorException {
        String message = "send message kafka by name #{" + kafkaReq.getName() + "}, id #{" + kafkaReq.getId() + "}";
        kafkaTemplate.send(KafkaConfig.KEY_NOTIFICATION, kafkaReq);
        kafkaTemplate.send(KafkaConfig.KEY_MESSAGE, message);
        return noContent();
    }
}
