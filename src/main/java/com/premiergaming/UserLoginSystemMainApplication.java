package com.premiergaming;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
public class UserLoginSystemMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserLoginSystemMainApplication.class, args);
    }
}