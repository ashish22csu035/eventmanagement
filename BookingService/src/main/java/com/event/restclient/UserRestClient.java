package com.event.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserRestClient {

    @Autowired
    private RestTemplate restTemplate;

    // ✅ Update URL according to your UserService port
    private static final String USER_SERVICE_URL = "http://localhost:9002/users";

    public String getUsernameById(int userId) {
        try {
            return restTemplate.getForObject(USER_SERVICE_URL + "/" + userId + "/name", String.class);
        } catch (Exception e) {
            System.out.println("⚠️ Failed to fetch username: " + e.getMessage());
            return "Unknown User";
        }
    }
}
