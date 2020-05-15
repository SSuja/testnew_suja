package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.RolePermission;
import com.tokyo.supermix.data.repositories.RolePermissionRepository;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
  @Autowired
  private RolePermissionRepository rolePermissionRepository;

  @Transactional(readOnly = true)
  public List<RolePermission> getAllRolePermissions() {
    return rolePermissionRepository.findAll();
  }

  @Override
  public List<RolePermission> saveRolePermission(List<RolePermission> rolePermission) {
    return rolePermissionRepository.saveAll(rolePermission);
  }

  @Override
  public boolean isDuplicateRowExists(Long roleId, Long permissionId) {
    if (rolePermissionRepository.existsByroleIdAndPermissionId(roleId, permissionId)) {
      return true;
    }
    return false;
  }

}
