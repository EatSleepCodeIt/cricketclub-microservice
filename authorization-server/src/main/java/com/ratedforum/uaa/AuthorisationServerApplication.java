package com.ratedforum.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableJpaRepositories(basePackages="com.ratedforum.uaa.repository")
public class AuthorisationServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorisationServerApplication.class, args);
    } 
}
