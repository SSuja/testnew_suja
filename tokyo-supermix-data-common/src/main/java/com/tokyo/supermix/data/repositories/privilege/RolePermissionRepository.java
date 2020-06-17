package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
  RolePermission findByRoleIdAndPermissionId(long roleId, Long permissionId);

  List<RolePermission> findByStatus(boolean status);

  List<RolePermission> findByRoleIdAndPermissionSubModuleId(Long roleId, Long subModuleId);
}
