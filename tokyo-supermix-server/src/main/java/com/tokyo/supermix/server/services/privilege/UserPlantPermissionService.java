package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;

public interface UserPlantPermissionService {
    
  public List<PermissionResponseDto> getPlantRolePermissionsByUserId(Long userId);
  public boolean isUserIdExist(Long userId);
 
}
