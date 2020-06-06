package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubRoutePermissionDto;
import com.tokyo.supermix.data.entities.privilege.MainRoute;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.entities.privilege.SubRoute;

public interface PrivilegeService {
  public void savePrivilege(RolePermission rolePermissions);
  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId);
  public List<PermissionResponseDto> getPermissions();
  public List<PrivilegeResponseDto> getPermissionsByRole(Long roleId);
  public List<SubRoute> getSubRoutesByMainRoute(String mainRoute);
  public List<MainRoute> getMainRoutes();
  public SubRoutePermissionDto getPermissionsBySubRoute(String subRoute);
//  public List<PermissionResponseDto> getPrivilegeBySubRoute(String subRoute);
//  
//  public PrivilegeResponseDto getPrivilegeByMainRoute(String subRoute);
  
}
