package com.anhmt.microservices.config;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringCloudConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder) {
        return locatorBuilder.routes()
                .route("product-service", r -> r.path("/product-service/**").uri("lb://product-service"))
                .route("user-service", r -> r.path("/user-service/**").uri("lb://user-service"))
                .build();
    }
}
