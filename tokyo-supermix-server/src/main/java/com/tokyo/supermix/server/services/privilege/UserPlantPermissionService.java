package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;

public interface UserPlantPermissionService {

  public List<PlantRolePlantPermissionResponseDto> getPlantRolePermissionsByUserId(Long userId);

  public boolean isUserIdExist(Long userId);

  public void saveUserPlantPermission(List<UserPlantPermission> userPlantPermissions);

  public List<PlantResponseDto> getByUserIdAndPermissionNameAndStatus(Long userId,
      String PermissionnName, Boolean status);

  public List<PlantRolePlantPermissionResponseDto> getByUserIdAndPlantCodeAndStatus(Long userId,
      String plantCode, boolean status);
  public boolean isPlantCodeExists(String plantCode,Long userId);
  
  public List<PlantResponseDto> getPlantsByNonPlantUserId(Long userId);
}
