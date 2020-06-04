package com.tokyo.supermix.data.repositories.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.privilege.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String roleName);
	Boolean existsByName(String roleName);
	Boolean existsByNameAndId(String roleName, Long Id);
}
