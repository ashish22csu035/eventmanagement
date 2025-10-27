package com.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.event") // ensures all subpackages are scanned
@EnableDiscoveryClient  // Add this

public class BookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
    }

    // ✅ This bean allows other classes to autowire RestTemplate successfully
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
