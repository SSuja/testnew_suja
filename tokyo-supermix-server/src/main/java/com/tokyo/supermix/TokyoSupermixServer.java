package com.tokyo.supermix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import ch.qos.logback.classic.BasicConfigurator;

@SpringBootApplication
@EnableScheduling
public class TokyoSupermixServer {

    public static void main(String[] args) {
    	org.apache.log4j.BasicConfigurator.configure();
        SpringApplication.run(TokyoSupermixServer.class, args);
    }

}