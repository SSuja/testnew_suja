package com.auth.security.service;

import java.util.List;
import com.tokyo.supermix.data.entities.auth.Role;

public interface RoleService {
  public void saveRole(Role role,List<Long> permissionIds);

  public boolean isRoleExists(Long id);

  public List<Role> getAllRoles();

  public void deleteRole(Long id);

  public Role findRoleById(Long id);

  public boolean existsByRoleName(String roleName);

  public boolean isUpdatedRoleExists(Long id, String roleName);
}
