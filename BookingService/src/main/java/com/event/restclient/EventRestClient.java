package com.event.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EventRestClient {

    @Autowired
    private RestTemplate restTemplate;

    // ✅ Update URL according to your EventService port
    private static final String EVENT_SERVICE_URL = "http://localhost:9003/events";

    public String getEventNameById(int eventId) {
        try {
            return restTemplate.getForObject(EVENT_SERVICE_URL + "/" + eventId + "/name", String.class);
        } catch (Exception e) {
            System.out.println("⚠️ Failed to fetch event name: " + e.getMessage());
            return "Unknown Event";
        }
    }
}
