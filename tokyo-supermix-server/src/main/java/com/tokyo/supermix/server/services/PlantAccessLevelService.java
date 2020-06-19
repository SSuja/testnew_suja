package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.privilege.PlantAccessLevel;

public interface PlantAccessLevelService {

  public boolean existsByPlantCodeAndPlantRoleId(String plantCode, Long plantRoleId);

  public void savePlantRolePlantCode(PlantAccessLevel plantAccessLevel);

  public boolean existsByPlantCodeAndStatus(String plantCode, boolean status);

  public List<PlantAccessLevel> getPlantRolesByPlantCodeAndStatus(String plantCode, boolean status);

}
