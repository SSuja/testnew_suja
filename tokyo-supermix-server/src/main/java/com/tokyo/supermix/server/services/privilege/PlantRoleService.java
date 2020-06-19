package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

public interface PlantRoleService {
  public void savePlantRole(PlantRole plantRole);

  public boolean existsByPlantCodeAndRoleId(String plantCode, Long roleId);
    List<PlantRole> getPlantRolesByRoleName(String roleName);

    List<PlantRole> getAllPlantRole(); 
}
