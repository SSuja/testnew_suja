package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionResponseDto;
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


  public void savePlantRolePlantPermission(PlantRolePlantPermission plantRolePlantPermission);


  public List<RolePermissionResponseDto> getPlantRolePermissionWithModuleByRoleId(Long plantRoleId);

  public PlantRolePlantPermission findByPlantRoleIdAndPlantPermissionId(Long plantRoleId,
      Long plantPermissionId);

  public void saveRolePermission(PlantRolePlantPermission plantRolePlantPermission);

  public boolean isPlantPermissionPlantCodeExist(String plantCode);

  public List<PlantRolePlantPermission> getPlantRolePermissionsByPlantRoleIdAndPlantPermissionPlantCode(Long plantRoleId,
      String plantCode);

}
