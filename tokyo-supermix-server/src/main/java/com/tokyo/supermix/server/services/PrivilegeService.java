package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubRoutePermissionDto;
import com.tokyo.supermix.data.entities.privilege.MainRoute;
import com.tokyo.supermix.data.entities.privilege.RolePermission;

public interface PrivilegeService {
  public void savePrivilege(RolePermission rolePermissions);
  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId);
  public List<PrivilegeResponseDto> getRoutePrivilegeByRole(Long roleId);
  public List<PrivilegeDto> getPrivilegeByRole(Long roleId);
  public List<PermissionResponseDto> getRoutePermissions();
  public List<PermissionDto> getSubRoutesByMainRoute(Long mainRouteId);
  public List<MainRoute> getMainRoutes();
  public SubRoutePermissionDto getPermissionsBySubRoute(String subRoute);
//  public List<PermissionResponseDto> getPrivilegeBySubRoute(String subRoute);
//  
//  public PrivilegeResponseDto getPrivilegeByMainRoute(String subRoute);
  public MainRoute findByMainRouteName(String mainRoute);
  public List<PermissionDto> getPermissions();

  
}
