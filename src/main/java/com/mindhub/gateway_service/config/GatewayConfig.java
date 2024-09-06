package com.mindhub.gateway_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;




@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator router(RouteLocatorBuilder builder){
    return builder.routes()
            .route("user-route", route -> route.path("api/users/**")
                    .filters(f -> f.filter(jwtAuthenticationFilter))
                    .uri("lb://user-service"))
            .route("userservice", route -> route.path("api/auth/**")
                    .uri("lb://user-service"))
            .route("taskservice", route -> route.path("api/tasks/**")
                    .filters(f -> f.filter(jwtAuthenticationFilter))
                    .uri("lb://task-service"))
            .build();
            
    }
}
