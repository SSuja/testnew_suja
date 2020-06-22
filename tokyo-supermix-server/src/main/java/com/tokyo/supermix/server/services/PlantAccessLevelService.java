package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PlantAccessLevelRequestDto;
import com.tokyo.supermix.data.entities.privilege.PlantAccessLevel;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

public interface PlantAccessLevelService {

  public boolean existsByPlantCodeAndPlantRoleId(String plantCode, Long plantRoleId);

  public void savePlantRolePlantCode(PlantAccessLevel plantAccessLevel);

  public boolean existsByPlantCodeAndStatus(String plantCode, boolean status);

  public List<PlantAccessLevel> getPlantRolesByPlantCodeAndStatus(String plantCode, boolean status);

  public void statusUpdate(PlantAccessLevelRequestDto plantAccessLevelRequestDto);

  void createPlantAccessLevel(PlantRole plantRole);
}
