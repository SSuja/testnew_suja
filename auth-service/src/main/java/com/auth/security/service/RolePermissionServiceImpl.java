package com.auth.security.service;

import java.util.List;
import com.tokyo.supermix.data.entities.auth.RolePermission;
import com.tokyo.supermix.data.repositories.auth.RolePermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    if (rolePermissionRepository.existsByRoleIdAndPermissionId(roleId, permissionId)) {
      return true;
    }
    return false;
  }

  @Transactional
  public RolePermission updateRolePermission(RolePermission rolePermission) {
    return rolePermissionRepository.save(rolePermission);
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
