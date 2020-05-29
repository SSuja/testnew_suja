package com.tokyo.supermix.data.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long>{

}