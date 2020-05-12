package com.tokyo.supermix.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan("com.tokyo.supermix.data")
@SpringBootApplication
public class TokyoSupermixAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokyoSupermixAuthenticationApplication.class, args);
	}

}
