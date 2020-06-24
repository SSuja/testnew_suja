package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

public interface PlantRoleService {
  public PlantRole savePlantRole(String plantCode, Long roleId);

  public boolean existsByPlantCodeAndRoleId(String plantCode, Long roleId);

  public List<PlantRole> getPlantRolesByRoleName(String roleName);

  public List<PlantRole> getAllPlantRole();

  public List<PlantRole> getAllPlantRolesByPlantCode(String plantCode);

  public boolean existsByPlantCode(String plantCode);



}
