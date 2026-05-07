package com.especialista.spring.jpa.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry // também tem como externalizar para um arquivo properties
public class RetryConfig {
}
