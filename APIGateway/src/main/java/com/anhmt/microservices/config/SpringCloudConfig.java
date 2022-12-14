package com.anhmt.microservices.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder locatorBuilder) {
//        return locatorBuilder.routes()
//                .route(r -> r.path("products-service")
//                        .uri("lb://PRODUCTS-SERVICE"))
//                .route(r -> r.path("users-service")
//                        .uri("lb://USERS-SERVICE"))
//                .build();
//    }
}
