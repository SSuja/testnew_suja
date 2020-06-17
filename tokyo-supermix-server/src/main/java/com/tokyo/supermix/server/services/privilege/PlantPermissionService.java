package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;

public interface PlantPermissionService {
  public List<String> getPlantsByPermissionName(String permissionName);

  public List<PlantPermission> getAllPlantsByPermissions();

  public void savePlantPermission(Plant plant);
}