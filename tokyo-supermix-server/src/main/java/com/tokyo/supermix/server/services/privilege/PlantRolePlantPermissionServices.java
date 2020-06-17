package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;

public interface PlantRolePlantPermissionServices {
	
//	 public List<PlantRolePlantPermissionResponseDto> getAllRolePlantPermissions();
  
  public List<PlantRolePlantPermissionResponseDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleID(Long plantRoleId ,Long subModuleId);
  
  public List<PlantRolePlantPermissionResponseDto> getByPlantRoleIdAndStatus(Long plantRoleId , Boolean status);

}
