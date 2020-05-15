package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.RolePermission;

public interface RolePermissionService {
  public List<RolePermission> getAllRolePermissions();
}
