package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;

public interface PlantRolePlantPermissionServices {

  public List<PlantRolePlantPermissionDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleID(
      Long plantRoleId, Long subModuleId);

  public List<PlantRolePlantPermissionDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleIDAndStatus(
      Long plantRoleId, Long subModuleId, Boolean status);

  public List<PlantRolePlantPermissionDto> getByPlantRoleIdAndStatus(Long plantRoleId,
      Boolean status);

  public List<PlantRolePlantPermissionDto> getPlantRolePermissionsByPlantRoleId(Long plantRoleId);

  public boolean isPlantRoleIdExist(Long plantRoleId);

  public void createPlantRolePlantPermission(PlantRole plantRole);

  public List<PlantRolePlantPermissionResponseDto> getPlantRolePermissionWithModuleByRoleId(Long plantRoleId);

  public PlantRolePlantPermission findByPlantRoleIdAndPlantPermissionId(Long plantRoleId,
      Long plantPermissionId);

  public void savePlantRolePlantPermission(List<PlantRolePlantPermission> plantRolePlantPermissions);

  public boolean isPlantPermissionPlantCodeExist(String plantCode);

  public List<PlantRolePlantPermission> getPlantRolePermissionsByPlantRoleIdAndPlantPermissionPlantCode(
      Long plantRoleId, String plantCode);

  public List<PlantResponseDto> getByPlantRoleIdAndPermissionNameAndStatus(Long plantRoleId,
      String permissionName, Boolean status);
}
