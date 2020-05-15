package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>{

}
