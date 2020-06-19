package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

public interface PlantRoleService {
  public PlantRole savePlantRole(PlantRole plantRole);

  public boolean existsByPlantCodeAndRoleId(String plantCode, Long roleId);

  public List<PlantRole> getPlantRolesByRoleName(String roleName);

  public List<PlantRole> getAllPlantRole();
}
