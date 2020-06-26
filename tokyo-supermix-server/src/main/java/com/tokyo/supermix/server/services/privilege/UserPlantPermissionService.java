package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;

public interface UserPlantPermissionService {

  public List<PlantRolePlantPermissionResponseDto> getPlantRolePermissionsByUserId(Long userId);

  public boolean isUserIdExist(Long userId);

  public void saveUserPlantPermission(List<UserPlantPermission> userPlantPermissions);
}