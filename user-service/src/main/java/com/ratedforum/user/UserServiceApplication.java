package com.ratedforum.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.netflix.config.DynamicPropertyFactory;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableTurbine
@EnableScheduling
@EnableJpaRepositories(basePackages="com.ratedforum.user.repository")
//@ImportResource("queue.xml")
public class UserServiceApplication {

    public static void main(String[] args) {
    	SpringApplication.run(UserServiceApplication.class, args);
    }
    
    @Bean
    public DynamicPropertyFactory getDynamicPropertyFactory(){
    	return DynamicPropertyFactory.getInstance();
    }
}