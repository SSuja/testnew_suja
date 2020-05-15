package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.RolePermission;

public interface RolePermissionService {
  public List<RolePermission> getAllRolePermissions();

  public List<RolePermission> saveRolePermission(List<RolePermission> rolePermission);

  public boolean isDuplicateRowExists(Long roleId, Long permissionId);
}
