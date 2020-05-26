package com.gateway.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableEurekaClient   
@EnableZuulProxy
@EntityScan("com.tokyo.supermix.data.entities")
@EnableJpaRepositories("com.tokyo.supermix.data.repositories")
@EnableDiscoveryClient
@SpringBootApplication
public class GateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

}
