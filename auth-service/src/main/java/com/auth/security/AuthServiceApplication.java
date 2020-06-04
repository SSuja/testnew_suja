package com.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.tokyo.supermix.data.repositories.auth.RolePermissionRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;

@EntityScan("com.tokyo.supermix.data.entities")
@EnableJpaRepositories("com.tokyo.supermix.data.repositories")
@EnableEurekaClient
@SpringBootApplication
public class AuthServiceApplication implements CommandLineRunner{
@Autowired
private RoleRepository roleRepository;
@Autowired
private RolePermissionRepository rolePermissionRepository;
	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.findByName("ADMIN").getRolePermission().forEach(obj->{
			System.out.println("permissions "+obj.getPermission().getName());
		});
		System.out.println("********** one permission "+rolePermissionRepository.findByRoleIdAndPermissionId(1L, 3L).getPermission().getName());
	}

}
