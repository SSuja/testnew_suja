package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;

public interface PlantRolePlantPermissionServices {
  public List<PlantRolePlantPermissionDto> getPlantRolePermissionsByPlantRoleId(Long plantRoleId);

  public boolean isPlantRoleIdExist(Long plantRoleId);
}
