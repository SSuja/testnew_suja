package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PlantPermissionRequestDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.privilege.Permission;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantPermissionRepository;

@Service
public class PlantPermissionServiceImpl implements PlantPermissionService {
  @Autowired
  private PlantPermissionRepository plantPermissionRepository;
  @Autowired
  private PermissionRepository permissionRepository;

  @Transactional(readOnly = true)
  public List<String> getPlantsByPermissionName(String permissionName) {
    List<PlantPermission> plantPermissions =
        plantPermissionRepository.findByPermissionName(permissionName);
    List<String> plants = new ArrayList<>();
    plantPermissions.forEach(plantPermission -> {
      plants.add(plantPermission.getPlant().getCode());
    });
    return plants;
  }

  @Transactional(readOnly = true)
  public List<PlantPermission> getAllPlantsByPermissions() {
    return plantPermissionRepository.findAll();
  }

  @Transactional
  public void savePlantPermission(Plant plant) {
    List<Permission> permissions = permissionRepository.findAll();
    for (Permission permission : permissions) {
      PlantPermission plantPermission = new PlantPermission();
      plantPermission.setPermission(permission);
      plantPermission.setPlant(plant);
      plantPermission.setName(plant.getCode() + "_" + permission.getName());
      plantPermissionRepository.save(plantPermission);
    }
  }

  @Transactional(readOnly = true)
  public List<PlantPermissionRequestDto> getPlantPermissionByPlantCodeAndMainModuleAndSubModule(
      String plantCode, Long subModuleId, Long mainModuleId) {
    List<PlantPermission> plantPermissionList = plantPermissionRepository
        .findByPlantCodeAndPermissionSubModuleIdAndPermissionSubModuleMainModuleId(plantCode,
            subModuleId, mainModuleId);
    List<PlantPermissionRequestDto> plantPermissionRequestDtolist =
        new ArrayList<PlantPermissionRequestDto>();
    plantPermissionList.forEach(plantPermission -> {

      PlantPermissionRequestDto plantPermissionRequestDto = new PlantPermissionRequestDto();
      plantPermissionRequestDto.setPermissionId(plantPermission.getId());
      plantPermissionRequestDto.setName(plantPermission.getName());
      plantPermissionRequestDto.setPlantCode(plantPermission.getPlant().getCode());
      plantPermissionRequestDtolist.add(plantPermissionRequestDto);
    });

    return plantPermissionRequestDtolist;
   
  public boolean isPermissionNameExists(String permissionName) {
    return plantPermissionRepository.existsByPermissionName(permissionName);
  }
}
