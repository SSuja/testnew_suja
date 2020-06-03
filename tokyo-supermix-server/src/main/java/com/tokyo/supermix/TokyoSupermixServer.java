package com.tokyo.supermix;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EntityScan("com.tokyo.supermix.data.entities")
@EnableJpaRepositories("com.tokyo.supermix.data.repositories")
public class TokyoSupermixServer {

    public static void main(String[] args) {
      BasicConfigurator.configure();
        SpringApplication.run(TokyoSupermixServer.class, args);
    }

}