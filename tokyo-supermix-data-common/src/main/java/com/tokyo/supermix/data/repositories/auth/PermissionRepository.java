package com.tokyo.supermix.data.repositories.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
  List<Permission> findByRolePermissionRoleId(Long roleId);

  List<Permission> findBySubModuleId(Long subModuleId);

  List<Permission> findBySubModuleName(String subModule);
}
