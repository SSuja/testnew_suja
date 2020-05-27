package com.auth.security.service;

import java.util.List;
import com.tokyo.supermix.data.entities.auth.RolePermission;

public interface RolePermissionService {
  public List<RolePermission> getAllRolePermissions();

  public List<RolePermission> saveRolePermission(List<RolePermission> rolePermission);

  public boolean isDuplicateRowExists(Long roleId, Long permissionId);

  public RolePermission updateRolePermission(RolePermission rolePermission);

  public boolean isRolePermissionExist(Long id);

  public void deleteRolePermission(Long id);
}
