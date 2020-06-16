package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.RolePermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionResponseDto;
import com.tokyo.supermix.data.entities.privilege.RolePermission;

public interface RolePermissionService {
  public void saveRolePermission(RolePermission rolePermissions);

  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId);

  public List<RolePermissionRequestDto> getRolePermissionByRole(Long roleId);

  public List<RolePermission> getRolePermissionByStatus(boolean status);

  public List<RolePermissionResponseDto> getRolePermissionWithModuleByRoleId(Long roleId);

}
