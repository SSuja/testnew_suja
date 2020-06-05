package com.tokyo.supermix.server.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.repositories.privilege.RolePermissionRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
  @Autowired
  private RolePermissionRepository rolePermissionRepository;

  @Transactional
  public void savePrivilege(RolePermission rolePermission) {
    rolePermissionRepository.save(rolePermission);
  }

  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId) {
    return rolePermissionRepository.findByRoleIdAndPermissionId(permissionId, roleId);
  }

}
