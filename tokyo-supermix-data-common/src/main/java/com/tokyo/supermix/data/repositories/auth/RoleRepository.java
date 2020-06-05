package com.tokyo.supermix.data.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String roleName);
  Boolean existsByName(String roleName);
}
