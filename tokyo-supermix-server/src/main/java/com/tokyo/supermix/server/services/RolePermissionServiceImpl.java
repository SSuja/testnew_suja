package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

  @Transactional
  public RolePermission updateRolePermission(RolePermission rolePermission) {
    return rolePermissionRepository.save(rolePermission);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(Long roleId, Long permissionId) {
    if (rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, permissionId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isRolePermissionExist(Long id) {
    return rolePermissionRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteRolePermission(Long id) {
    rolePermissionRepository.deleteById(id);
  }
}
