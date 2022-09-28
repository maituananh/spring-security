package com.springsecurity.jwtsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.springsecurity.jwtsecurity.domain"})  // scan JPA entities
public class JwtSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtSecurityApplication.class, args);
    }

}
