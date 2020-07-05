package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PlantPermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubModulePlantRolePlantPermissionDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.privilege.MainModule;
import com.tokyo.supermix.data.entities.privilege.Permission;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.entities.privilege.SubModule;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.MainModuleRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantPermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.SubModuleRepository;

@Service
public class PlantPermissionServiceImpl implements PlantPermissionService {
  @Autowired
  private PlantPermissionRepository plantPermissionRepository;
  @Autowired
  private PermissionRepository permissionRepository;
  @Autowired
  private MainModuleRepository mainModuleRepository;
  @Autowired
  private SubModuleRepository subModuleRepository;

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
  public List<PlantRolePlantPermissionResponseDto> getAllPlantsByPermissions() {
    return getPlantPermissionsByMainModule(mainModuleRepository.findAll());
  }

  private List<PlantRolePlantPermissionResponseDto> getPlantPermissionsByMainModule(
      List<MainModule> findAll) {
    List<PlantRolePlantPermissionResponseDto> plantPermissionMainModuleDtolist =
        new ArrayList<PlantRolePlantPermissionResponseDto>();
    mainModuleRepository.findAll().forEach(main -> {
      PlantRolePlantPermissionResponseDto plantPermissionMainModuleDto =
          new PlantRolePlantPermissionResponseDto();
      plantPermissionMainModuleDto.setMainModule(main.getName());
      plantPermissionMainModuleDto.setMainModuleId(main.getId());
      List<SubModule> subModuleList = subModuleRepository.findByMainModuleId(main.getId());
      plantPermissionMainModuleDto
          .setSubModules(getPlantPermissionSubModulesByMainModuleId(subModuleList, main.getId()));;
      plantPermissionMainModuleDtolist.add(plantPermissionMainModuleDto);
    });
    return plantPermissionMainModuleDtolist;
  }

  private List<SubModulePlantRolePlantPermissionDto> getPlantPermissionSubModulesByMainModuleId(
      List<SubModule> subModuleList, Long mainModuleId) {
    List<SubModulePlantRolePlantPermissionDto> SubModuleRolePermissionDtoList =
        new ArrayList<SubModulePlantRolePlantPermissionDto>();
    for (SubModule sub : subModuleList) {
      SubModulePlantRolePlantPermissionDto plantPermissionSubModuleDto =
          new SubModulePlantRolePlantPermissionDto();
      plantPermissionSubModuleDto.setSubModule(sub.getName());
      plantPermissionSubModuleDto.setSubModuleId(sub.getId());
      plantPermissionSubModuleDto.setMainModuleId(mainModuleId);
      plantPermissionSubModuleDto.setPrivilages(
          getPlantPermissionsBySubModuleId(sub.getId(), mainModuleId));
      SubModuleRolePermissionDtoList.add(plantPermissionSubModuleDto);
    }
    return SubModuleRolePermissionDtoList;
  }

  private List<PlantRolePlantPermissionRequestDto> getPlantPermissionsBySubModuleId(
      Long subModuleId, Long mainModuleId) {
    List<PlantRolePlantPermissionRequestDto> plantPermissionResponseDtos =
        new ArrayList<PlantRolePlantPermissionRequestDto>();
    List<PlantPermission> plantPermissions =
        plantPermissionRepository.findByPermissionSubModuleId(subModuleId);
    for (PlantPermission plantPermission : plantPermissions) {
      PlantRolePlantPermissionRequestDto plantPermissionResponseDto =
          new PlantRolePlantPermissionRequestDto();
      plantPermissionResponseDto.setPlantPermissionName(plantPermission.getName());
      plantPermissionResponseDto.setPlantPermissionId(plantPermission.getId());
      plantPermissionResponseDto.setMainModuleId(mainModuleId);
      plantPermissionResponseDto.setSubModuleId(subModuleId);
      plantPermissionResponseDtos.add(plantPermissionResponseDto);
    }
    return plantPermissionResponseDtos;
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
  public List<PlantPermissionResponseDto> getPlantPermissionByPlantCodeAndMainModuleAndSubModule(
      String plantCode, Long subModuleId, Long mainModuleId) {
    List<PlantPermission> plantPermissionList = plantPermissionRepository
        .findByPlantCodeAndPermissionSubModuleIdAndPermissionSubModuleMainModuleId(plantCode,
            subModuleId, mainModuleId);
    List<PlantPermissionResponseDto> plantPermissionResponseDtolist =
        new ArrayList<PlantPermissionResponseDto>();
    plantPermissionList.forEach(plantPermission -> {
      PlantPermissionResponseDto plantPermissionResponseDto = new PlantPermissionResponseDto();
      plantPermissionResponseDto.setId(plantPermission.getId());
      plantPermissionResponseDto.setName(plantPermission.getName());
      plantPermissionResponseDto.setPermissionName(plantPermission.getPermission().getName());
      plantPermissionResponseDto.setPlantCode(plantPermission.getPlant().getCode());
      plantPermissionResponseDtolist.add(plantPermissionResponseDto);
    });

    return plantPermissionResponseDtolist;
  }

  public boolean isPermissionNameExists(String permissionName) {
    return plantPermissionRepository.existsByPermissionName(permissionName);
  }
}
