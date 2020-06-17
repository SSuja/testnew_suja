package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionResponseDto;

public interface PlantRolePlantPermissionServices {
  public List<PlantRolePlantPermissionDto> getPlantRolePermissionsByPlantRoleId(Long plantRoleId);

  public boolean isPlantRoleIdExist(Long plantRoleId);

  public List<RolePermissionResponseDto> getPlantRolePermissionWithModuleByRoleId(Long plantRoleId);
}
