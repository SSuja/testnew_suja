package com.tokyo.supermix.server.services;

import com.tokyo.supermix.data.entities.privilege.RolePermission;

public interface PrivilegeService {
  public void savePrivilege(RolePermission rolePermissions);

  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId);
}
