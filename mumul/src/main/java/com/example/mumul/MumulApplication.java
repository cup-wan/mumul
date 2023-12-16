package com.example.mumul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class MumulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MumulApplication.class, args);
    }

}
