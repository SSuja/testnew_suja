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
      for (SubModule sub : subModuleList) {
        SubModulePlantRolePlantPermissionDto subModulePlantRolePlantPermissionDto =
            new SubModulePlantRolePlantPermissionDto();
        subModulePlantRolePlantPermissionDto.setMainModuleId(main.getId());
        subModulePlantRolePlantPermissionDto.setSubModuleId(sub.getId());
        subModulePlantRolePlantPermissionDto.setSubModule(sub.getName());
        boolean subStatus = false;
        List<PlantRolePlantPermissionRequestDto> rolePermissionDtoList =
            new ArrayList<PlantRolePlantPermissionRequestDto>();
        List<PlantRolePlantPermission> plantRolePlantPermissionList =
            plantRolePlantPermissionRepository
                .findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(plantRoleId, sub.getId());
        for (PlantRolePlantPermission permission : plantRolePlantPermissionList) {
          PlantRolePlantPermissionRequestDto plantRolePlantPermissionRequestDto =
              new PlantRolePlantPermissionRequestDto();
          plantRolePlantPermissionRequestDto.setPlantPermissionName(permission.getPlantPermission().getPermission().getName());
          plantRolePlantPermissionRequestDto.setPlantPermissionId(permission.getId());
          plantRolePlantPermissionRequestDto.setPlantRoleId(plantRoleId);
          plantRolePlantPermissionRequestDto.setStatus(permission.isStatus());
          plantRolePlantPermissionRequestDto.setSubModuleId(sub.getId());
          plantRolePlantPermissionRequestDto.setMainModuleId(main.getId());
          if (permission.isStatus()) {
            subStatus = true;
          }
          rolePermissionDtoList.add(plantRolePlantPermissionRequestDto);
        }
        subModulePlantRolePlantPermissionDto.setStatus(subStatus);
        subModulePlantRolePlantPermissionDto.setPlantRolePlantPermissions(rolePermissionDtoList);
        subModulePlantRolePlantPermissionDtoList.add(subModulePlantRolePlantPermissionDto);
        if (subStatus) {
          mainStatus = true;
        }
      }
      plantRolePlantPermissionResponseDto.setStatus(mainStatus);
      plantRolePlantPermissionResponseDto.setSubModules(subModulePlantRolePlantPermissionDtoList);
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });
    return plantRolePlantPermissionResponseDtolist;
  }

  @Transactional(readOnly = true)
  public PlantRolePlantPermission findByPlantRoleIdAndPlantPermissionId(Long plantRoleId,
      Long plantPermissionId) {
    return plantRolePlantPermissionRepository.findByPlantRoleIdAndPlantPermissionId(plantRoleId,
        plantPermissionId);
  }

  @Transactional
  public void savePlantRolePlantPermission(PlantRolePlantPermission plantRolePlantPermission) {
    plantRolePlantPermissionRepository.save(plantRolePlantPermission);
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
    List<PlantRolePlantPermission> plantRolePlantPermissionList =
        new ArrayList<PlantRolePlantPermission>();
    plantPermissionList.forEach(plantPermission -> {
      PlantRolePlantPermission plantRolePlantPermission = new PlantRolePlantPermission();
      plantRolePlantPermission.setPlantPermission(plantPermission);
      plantRolePlantPermission.setStatus(false);
      plantRolePlantPermission.setPlantRole(plantRole);
      plantRolePlantPermissionList.add(plantRolePlantPermission);
    });
    plantRolePlantPermissionRepository.saveAll(plantRolePlantPermissionList);
  }
}
