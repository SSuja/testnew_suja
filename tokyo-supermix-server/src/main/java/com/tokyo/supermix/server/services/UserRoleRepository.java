package com.tokyo.supermix.server.services;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
