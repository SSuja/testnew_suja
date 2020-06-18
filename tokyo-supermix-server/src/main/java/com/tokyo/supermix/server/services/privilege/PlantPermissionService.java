package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantPermissionResponseDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;

public interface PlantPermissionService {
  public List<String> getPlantsByPermissionName(String permissionName);

  public List<PlantPermission> getAllPlantsByPermissions();

  public void savePlantPermission(Plant plant);

  public List<PlantPermissionResponseDto> getPlantPermissionByPlantCodeAndMainModuleAndSubModule(
      String plantCode, Long subModuleId, Long mainModuleId);
  public boolean isPermissionNameExists(String permissionName);
}
