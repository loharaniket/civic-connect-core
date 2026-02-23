package com.civic_connect_core.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "spring.host")
@Data
public class HostConfig {
    private String server;
    private String frontend;
}
