package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeResponseDto;
import com.tokyo.supermix.data.entities.privilege.RolePermission;

public interface PrivilegeService {
  public void savePrivilege(RolePermission rolePermissions);

  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId);

  public List<PermissionResponseDto> getPermissions();

  public List<PrivilegeResponseDto> getPermissionsByRole(Long roleId);
  
//  public PrivilegeResponseDto getPrivilegeBySubRoute(String subRoute);
//  
//  public PrivilegeResponseDto getPrivilegeByMainRoute(String subRoute);
  
}
