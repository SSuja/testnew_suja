package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PlantResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubModulePlantRolePlantPermissionDto;
import com.tokyo.supermix.data.entities.privilege.MainModule;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;
import com.tokyo.supermix.data.entities.privilege.SubModule;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.privilege.MainModuleRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantPermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantRolePlantPermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.SubModuleRepository;

@Service
public class PlantRolePlantPermissionServiceImpl implements PlantRolePlantPermissionServices {
  @Autowired
  private PlantRolePlantPermissionRepository plantRolePlantPermissionRepository;
  @Autowired
  private PlantPermissionRepository plantPermissionRepository;
  @Autowired
  private MainModuleRepository mainModuleRepository;
  @Autowired
  private SubModuleRepository subModuleRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleID(
      Long plantRoleId, Long subModuleId) {
    List<PlantRolePlantPermission> plantRolePlantPermissionList = plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(plantRoleId, subModuleId);
    return mapper.map(plantRolePlantPermissionList, PlantRolePlantPermissionDto.class);
  }

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionDto> getByPlantRoleIdAndStatus(Long plantRoleId,
      Boolean status) {
    List<PlantRolePlantPermission> plantRolePlantPermissionList =
        plantRolePlantPermissionRepository.findByPlantRoleIdAndStatus(plantRoleId, status);
    return mapper.map(plantRolePlantPermissionList, PlantRolePlantPermissionDto.class);
  }

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionDto> getPlantRolePermissionsByPlantRoleId(Long plantRoleId) {
    List<PlantRolePlantPermission> plantRolePlantPermissionList =
        plantRolePlantPermissionRepository.findByPlantRoleId(plantRoleId);
    return mapper.map(plantRolePlantPermissionList, PlantRolePlantPermissionDto.class);
  }

  @Transactional(readOnly = true)
  public boolean isPlantRoleIdExist(Long plantRoleId) {
    return plantRolePlantPermissionRepository.existsByPlantRoleId(plantRoleId);
  }

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleIDAndStatus(
      Long plantRoleId, Long subModuleId, Boolean status) {
    List<PlantRolePlantPermission> plantRolePlantPermissionList = plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPermissionSubModuleIdAndStatus(plantRoleId, subModuleId,
            status);
    return mapper.map(plantRolePlantPermissionList, PlantRolePlantPermissionDto.class);
  }

  public List<PlantRolePlantPermissionResponseDto> getPlantRolePermissionWithModuleByRoleId(
      Long plantRoleId) {
    return getMainModulesWithStatusByPlantRoleId(mainModuleRepository.findAll(), plantRoleId);
  }

  private List<PlantRolePlantPermissionResponseDto> getMainModulesWithStatusByPlantRoleId(
      List<MainModule> MainModuleList, Long plantRoleId) {
    List<PlantRolePlantPermissionResponseDto> plantRolePlantPermissionResponseDtolist =
        new ArrayList<PlantRolePlantPermissionResponseDto>();
    mainModuleRepository.findAll().forEach(main -> {
      PlantRolePlantPermissionResponseDto plantRolePlantPermissionResponseDto =
          new PlantRolePlantPermissionResponseDto();
      plantRolePlantPermissionResponseDto.setMainModuleId(main.getId());
      plantRolePlantPermissionResponseDto.setMainModule(main.getName());
      boolean mainStatus = false;
      List<SubModulePlantRolePlantPermissionDto> subModulePlantRolePlantPermissionDtoList =
          new ArrayList<SubModulePlantRolePlantPermissionDto>();
      List<SubModule> subModuleList = subModuleRepository.findByMainModuleId(main.getId());
      boolean status = getSubModulesByPlantRoleIdAndReturnMainStatus(subModuleList, plantRoleId,
          main.getId(), subModulePlantRolePlantPermissionDtoList, mainStatus);
      plantRolePlantPermissionResponseDto.setStatus(status);
      plantRolePlantPermissionResponseDto.setSubModules(subModulePlantRolePlantPermissionDtoList);
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });
    return plantRolePlantPermissionResponseDtolist;
  }

  private boolean getSubModulesByPlantRoleIdAndReturnMainStatus(List<SubModule> subModuleList,
      Long plantRoleId, Long mainModuleId,
      List<SubModulePlantRolePlantPermissionDto> subModulePlantRolePlantPermissionDtoList,
      boolean mainStatus) {
    for (SubModule sub : subModuleList) {
      SubModulePlantRolePlantPermissionDto subModulePlantRolePlantPermissionDto =
          new SubModulePlantRolePlantPermissionDto();
      subModulePlantRolePlantPermissionDto.setMainModuleId(mainModuleId);
      subModulePlantRolePlantPermissionDto.setSubModuleId(sub.getId());
      subModulePlantRolePlantPermissionDto.setSubModule(sub.getName());
      boolean subStatus = false;
      List<PlantRolePlantPermissionRequestDto> rolePermissionDtoList =
          new ArrayList<PlantRolePlantPermissionRequestDto>();
      List<PlantRolePlantPermission> plantRolePlantPermissionList =
          plantRolePlantPermissionRepository
              .findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(plantRoleId, sub.getId());
      boolean status = getPlantPermissionsAndReturnSubModuleStatus(plantRolePlantPermissionList,
          subStatus, plantRoleId, sub.getId(), mainModuleId, rolePermissionDtoList);
      subModulePlantRolePlantPermissionDto.setStatus(status);
      subModulePlantRolePlantPermissionDto.setPrivilages(rolePermissionDtoList);
      subModulePlantRolePlantPermissionDtoList.add(subModulePlantRolePlantPermissionDto);
      if (status) {
        mainStatus = true;
      }
    }
    return mainStatus;
  }

  private boolean getPlantPermissionsAndReturnSubModuleStatus(
      List<PlantRolePlantPermission> plantRolePlantPermissionList, boolean subStatus,
      Long plantRoleId, Long subModuleId, Long mainModuleId,
      List<PlantRolePlantPermissionRequestDto> rolePermissionDtoList) {
    for (PlantRolePlantPermission plantRolePlantpermission : plantRolePlantPermissionList) {
      PlantRolePlantPermissionRequestDto plantRolePlantPermissionRequestDto =
          new PlantRolePlantPermissionRequestDto();
      plantRolePlantPermissionRequestDto
          .setPlantPermissionName(plantRolePlantpermission.getPlantPermission().getName());
      plantRolePlantPermissionRequestDto.setPlantPermissionId(plantRolePlantpermission.getPlantPermission().getId());
      plantRolePlantPermissionRequestDto.setPlantRoleId(plantRoleId);
      plantRolePlantPermissionRequestDto.setStatus(plantRolePlantpermission.isStatus());
      plantRolePlantPermissionRequestDto.setSubModuleId(subModuleId);
      plantRolePlantPermissionRequestDto.setMainModuleId(mainModuleId);
      if (plantRolePlantpermission.isStatus()) {
        subStatus = true;
      }
      rolePermissionDtoList.add(plantRolePlantPermissionRequestDto);
    }
    return subStatus;
  }

  @Transactional(readOnly = true)
  public PlantRolePlantPermission findByPlantRoleIdAndPlantPermissionId(Long plantRoleId,
      Long plantPermissionId) {
    return plantRolePlantPermissionRepository.findByPlantRoleIdAndPlantPermissionId(plantRoleId,
        plantPermissionId);
  }

  @Transactional
  public void savePlantRolePlantPermission(
      List<PlantRolePlantPermission> plantRolePlantPermissios) {
    for (PlantRolePlantPermission plantRolePlantPermission : plantRolePlantPermissios) {
      PlantRolePlantPermission plantRolePlantPermission2 = plantRolePlantPermissionRepository
          .findByPlantRoleIdAndPlantPermissionId(plantRolePlantPermission.getPlantRole().getId(),
              plantRolePlantPermission.getPlantPermission().getPermission().getId());
      if (plantRolePlantPermission2 != null) {
        plantRolePlantPermission2.setStatus(plantRolePlantPermission.isStatus());
        plantRolePlantPermissionRepository.save(plantRolePlantPermission2);
      } else {
        plantRolePlantPermissionRepository.save(plantRolePlantPermission);
      }
    }
  }

  @Transactional(readOnly = true)
  public boolean isPlantPermissionPlantCodeExist(String plantCode) {
    return plantRolePlantPermissionRepository.existsByPlantPermissionPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermission> getPlantRolePermissionsByPlantRoleIdAndPlantPermissionPlantCode(
      Long plantRoleId, String plantCode) {
    return plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPlantCode(plantRoleId, plantCode);
  }

  @Transactional(readOnly = true)
  public List<PlantResponseDto> getByPlantRoleIdAndPermissionNameAndStatus(Long plantRoleId,
      String permissionName, Boolean status) {
    List<PlantRolePlantPermission> plantRolePlantPermissionList = plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPermissionNameAndStatus(plantRoleId, permissionName,
            status);
    List<PlantResponseDto> plantResponseDtolist = new ArrayList<PlantResponseDto>();
    plantRolePlantPermissionList.forEach(plantRolePlantPermission -> {
      plantResponseDtolist.add(mapper.map(plantRolePlantPermission.getPlantPermission().getPlant(),
          PlantResponseDto.class));
    });
    return plantResponseDtolist;
  }

  @Transactional
  public void createPlantRolePlantPermission(PlantRole plantRole) {
    List<PlantPermission> plantPermissionList =
        plantPermissionRepository.findByPlantCode(plantRole.getPlant().getCode());
    plantPermissionList.forEach(plantPermission -> {
      PlantRolePlantPermission plantRolePlantPermission = new PlantRolePlantPermission();
      plantRolePlantPermission.setPlantPermission(plantPermission);
      plantRolePlantPermission.setStatus(false);
      plantRolePlantPermission.setPlantRole(plantRole);
      System.out.println("plantroleplant permission save method --"
          + plantRolePlantPermission.getPlantPermission().getName());
      plantRolePlantPermissionRepository.save(plantRolePlantPermission);
    });
  }
}
