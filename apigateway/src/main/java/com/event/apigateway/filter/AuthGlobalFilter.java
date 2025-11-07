package com.event.apigateway.filter;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.event.apigateway.config.AuthHeaderFactory;
import com.event.apigateway.dto.AuthDto;

import reactor.core.publisher.Mono;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final WebClient.Builder webClientBuilder;
    private final AuthHeaderFactory authFactory;

    @Autowired
    public AuthGlobalFilter(WebClient.Builder webClientBuilder, AuthHeaderFactory authFactory) {
        this.webClientBuilder = webClientBuilder;
        this.authFactory = authFactory;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        String path = exchange.getRequest().getPath().toString();
        
        System.out.println("🔍 API Gateway - Request Path: " + path);

       
        if (path.startsWith("/auth/")) {
            System.out.println("✅ Auth endpoint - allowing without authentication");
            return chain.filter(exchange);
        }

       
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            System.out.println("❌ No Authorization header found");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

      
        String base64Credentials = authHeader.substring("Basic ".length()).trim();
        
     
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);

   
        String[] parts = decodedString.split(":", 2);
        String email = parts[0];
        String password = parts.length > 1 ? parts[1] : "";

        System.out.println("🔐 Validating user: " + email);

        AuthDto request = new AuthDto(email, password);
        WebClient client = webClientBuilder.build();
        
        Mono<ResponseEntity<Void>> responseMono = client.post()
                .uri("lb://AUTHENTICATIONSERVICE/auth/authenticate")
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity();

        return responseMono.flatMap(response -> {
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("✅ User authenticated successfully: " + email);
                
              
                Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                String routeId = (route != null) ? route.getId() : "default";
                
                System.out.println("🔄 Forwarding to service: " + routeId);

               
                String serviceAuthHeader = authFactory.buildAuthHeader(routeId);
                
              
                ServerHttpRequest mutatedRequest = exchange.getRequest()
                    .mutate()
                    .header(HttpHeaders.AUTHORIZATION, serviceAuthHeader)
                    .header("X-API-GATEWAY-SECRET", authFactory.getSharedSecret())
                    .build();
                
                System.out.println(" Request mutated with service credentials");
                
                return chain.filter(exchange.mutate().request(mutatedRequest).build());
            } else {
                System.out.println(" Authentication failed for user: " + email);
                return unauthorized(exchange);
            }
        })
        .onErrorResume(ex -> {
            System.out.println(" Error during authentication: " + ex.getMessage());
            return unauthorized(exchange);
        });
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
      
        return -1;
    }
}