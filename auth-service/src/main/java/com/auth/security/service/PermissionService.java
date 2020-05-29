package com.auth.security.service;

import java.util.List;
import com.tokyo.supermix.data.entities.auth.Permission;

public interface PermissionService {
  public List<Permission> getAllPermissions();
}
