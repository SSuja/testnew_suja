package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.RolePermission;

public interface RolePermissionService {
  public List<RolePermission> getAllRolePermissions();

  public RolePermission updateRolePermission(RolePermission rolePermission);

  public boolean isDuplicateEntryExist(Long roleId, Long permissionId);

  public boolean isRolePermissionExist(Long id);
}
