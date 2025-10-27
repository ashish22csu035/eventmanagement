package com.event.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // ✅ Marks this class as a configuration class for Spring Boot
public class ApplicationConfig {

    @Bean  // ✅ Registers this method’s return object as a Spring Bean
    public ModelMapper modelMapper() {
        return new ModelMapper(); // ✅ Creates and provides a single ModelMapper instance
    }
}
