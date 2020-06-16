package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;
import com.tokyo.supermix.data.dto.privilege.SubModulePermissionDto;

public interface PermissionService {
  public List<PermissionDto> getPermissions();

  public SubModulePermissionDto getPermissionsBySubModule(String subModule);

  public SubModulePermissionDto getPermissionsSubModuleName(String name);
}
