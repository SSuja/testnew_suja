package com.auth.security.service;

import java.util.List;

import com.tokyo.supermix.data.entities.privilege.Permission;

public interface PermissionService {
  public List<Permission> getAllPermissions();
}
