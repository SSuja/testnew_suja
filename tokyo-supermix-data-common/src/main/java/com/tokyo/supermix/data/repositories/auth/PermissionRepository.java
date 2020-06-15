package com.tokyo.supermix.data.repositories.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
 List<Permission> findByRolePermissionRoleId(Long roleId);
 List<Permission> findByRolePermissionRoleIdAndRolePermissionStatus(Long roleId,boolean status);
 List<Permission> findBySubModuleId(Long subModuleId);
 List<Permission> findBySubModuleName(String subModule);
 List<Permission> findByRolePermissionRoleIdAndSubModuleId(Long roleId,Long subModuleId);
}
