package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;

public interface UserPlantPermissionService {
    
  public List<PlantRolePlantPermissionResponseDto> getPlantRolePermissionsByUserId(Long userId);
  public boolean isUserIdExist(Long userId);
 
}
