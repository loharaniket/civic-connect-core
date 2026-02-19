package com.civic_connect_core.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all endpoints
                        .allowedOriginPatterns("*") // Allow all origins for testing (Change to your frontend URL in production)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // MUST include OPTIONS
                        .allowedHeaders("*") // Allow all headers (like Authorization)
                        .allowCredentials(true); // Required if you use cookies or Authorization headers
            }
        };
    }
}