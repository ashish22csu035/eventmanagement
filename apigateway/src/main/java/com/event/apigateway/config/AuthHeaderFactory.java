package com.event.apigateway.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthHeaderFactory {
    
    @Value("${bookingservice.auth.username}")
    private String bookingUsername;
    
    @Value("${bookingservice.auth.password}")
    private String bookingPassword;
    
    @Value("${eventservice.auth.username}")
    private String eventUsername;
    
    @Value("${eventservice.auth.password}")
    private String eventPassword;
    
    @Value("${userservice.auth.username}")
    private String userUsername;
    
    @Value("${userservice.auth.password}")
    private String userPassword;
    
    @Value("${apigateway.shared.secret}")
    private String sharedSecret;
    
    public String buildAuthHeader(String serviceName) {
        String username = "";
        String password = "";
        
        if ("bookingservice".equals(serviceName)) {
            username = bookingUsername;
            password = bookingPassword;
        } else if ("eventservice".equals(serviceName)) {
            username = eventUsername;
            password = eventPassword;
        } else if ("userservice".equals(serviceName)) {
            username = userUsername;
            password = userPassword;
        }
        
        String auth = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
    }
    
    public String getSharedSecret() {
        return sharedSecret;
    }
}