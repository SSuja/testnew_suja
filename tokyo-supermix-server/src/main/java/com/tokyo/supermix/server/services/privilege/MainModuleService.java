package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionRequestDto;
import com.tokyo.supermix.data.entities.privilege.MainModule;
import com.tokyo.supermix.data.entities.privilege.Permission;

public interface MainModuleService {
  public List<MainModule> getMainModules();

  public List<PermissionResponseDto> getModulePermissions();

  public MainModule findByMainModuleName(String mainModule);

  public List<RolePermissionRequestDto> setRolePermissionByRole(Long roleId,
      List<Permission> permissionList, List<RolePermissionRequestDto> rolePermissionDtoList);
}
