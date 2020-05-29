package com.tokyo.supermix.data.repositories.auth;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByRoleName(String roleName);
	Boolean existsByRoleName(String roleName);
	Boolean existsByRoleNameAndId(String roleName, Long Id);
}