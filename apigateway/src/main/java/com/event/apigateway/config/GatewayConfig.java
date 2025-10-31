package com.event.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            // Event Service Routes
            .route("eventservice", r -> r.path("/events/**")
                .uri("lb://EVENTSERVICE"))  //  Changed to UPPERCASE
            
            // Booking Service Routes
            .route("bookingservice", r -> r.path("/bookings/**")
                .uri("lb://BOOKINGSERVICE"))  //  Also uppercase for consistency
            
            // User Service Routes
            .route("userservice", r -> r.path("/users/**")
                .uri("lb://USERSERVICE"))  //  Also uppercase for consistency
            
            .build();
    }
}