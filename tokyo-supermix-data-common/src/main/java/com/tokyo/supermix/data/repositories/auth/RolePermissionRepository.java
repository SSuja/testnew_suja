package com.tokyo.supermix.data.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
  boolean existsByRoleIdAndPermissionId(Long roleId, Long permissionId);
}
