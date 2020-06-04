package com.tokyo.supermix.data.repositories.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tokyo.supermix.data.entities.privilege.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>{
	RolePermission findByRoleIdAndPermissionId(long roleId,Long permissionId);
}
