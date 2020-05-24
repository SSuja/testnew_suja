package com.gateway.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EntityScan("com.tokyo.supermix.data.entities.auth")
@EnableJpaRepositories("com.tokyo.supermix.data.repositories.auth")
@EnableEurekaClient   
@EnableZuulProxy
@SpringBootApplication
public class GatewayWithAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayWithAuthApplication.class, args);
	}

}
