package com.tokyo.supermix;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokyoSupermixServer {

    public static void main(String[] args) {
      BasicConfigurator.configure();
        SpringApplication.run(TokyoSupermixServer.class, args);
    }

}