package com.event.configurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
/*
 * http://localhost:8888/bookingservice/default
 * http://localhost:8888/event/default
 * http://localhost:8888/user/default
 * http://localhost:8888/apigateway/default
 * http://localhost:8888/servicediscovery/default
 * 
 */

@SpringBootApplication
@EnableConfigServer

public class ConfigurationserverApplication {

	public static void main(String[] args) {

        SpringApplication app = new SpringApplication(ConfigurationserverApplication.class);
        
        
        String mode = System.getenv("CONFIG_MODE");
        if ("native".equalsIgnoreCase(mode)) {
            app.setAdditionalProfiles("native");
        } else {
            app.setAdditionalProfiles("git");
        }
        app.run(args);
    }

}
