package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubModuleRolePermissionDto;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;
import com.tokyo.supermix.data.entities.privilege.SubModule;
import com.tokyo.supermix.data.repositories.privilege.MainModuleRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantRolePlantPermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.SubModuleRepository;

@Service
public class PlantRolePlantPermissionServiceImpl implements PlantRolePlantPermissionServices {
  @Autowired
  private PlantRolePlantPermissionRepository plantRolePlantPermissionRepository;
  @Autowired
  private MainModuleRepository mainModuleRepository;
  @Autowired
  private SubModuleRepository subModuleRepository;


  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionRequestDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleID(
      Long plantRoleId, Long subModuleId) {
    List<PlantRolePlantPermission> PlantRolePlantPermissionList = plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(plantRoleId, subModuleId);
    List<PlantRolePlantPermissionRequestDto> plantRolePlantPermissionResponseDtolist =
        new ArrayList<PlantRolePlantPermissionRequestDto>();
    PlantRolePlantPermissionList.forEach(permission -> {
      PlantRolePlantPermissionRequestDto plantRolePlantPermissionResponseDto =
          new PlantRolePlantPermissionRequestDto();
      plantRolePlantPermissionResponseDto.setId(permission.getId());
      plantRolePlantPermissionResponseDto.setName(permission.getPlantPermission().getName());
      plantRolePlantPermissionResponseDto.setPlantRoleId(permission.getPlantRole().getId());;
      plantRolePlantPermissionResponseDto.setStatus(permission.isStatus());
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });

    return plantRolePlantPermissionResponseDtolist;
  }

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionRequestDto> getByPlantRoleIdAndStatus(Long plantRoleId,
      Boolean status) {
    List<PlantRolePlantPermission> PlantRolePlantPermissionList =
        plantRolePlantPermissionRepository.findByPlantRoleIdAndStatus(plantRoleId, status);

    List<PlantRolePlantPermissionRequestDto> plantRolePlantPermissionResponseDtolist =
        new ArrayList<PlantRolePlantPermissionRequestDto>();
    PlantRolePlantPermissionList.forEach(permission -> {

      PlantRolePlantPermissionRequestDto plantRolePlantPermissionResponseDto =
          new PlantRolePlantPermissionRequestDto();
      plantRolePlantPermissionResponseDto.setId(permission.getId());
      plantRolePlantPermissionResponseDto.setName(permission.getPlantPermission().getName());
      plantRolePlantPermissionResponseDto.setPlantRoleId(permission.getPlantRole().getId());;
      plantRolePlantPermissionResponseDto.setStatus(permission.isStatus());
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });

    return plantRolePlantPermissionResponseDtolist;
  }

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionDto> getPlantRolePermissionsByPlantRoleId(Long plantRoleId) {
    List<PlantRolePlantPermission> plantRolePlantPermissionList =
        plantRolePlantPermissionRepository.findByPlantRoleId(plantRoleId);
    List<PlantRolePlantPermissionDto> plantRolePlantPermissionDtoList =
        new ArrayList<PlantRolePlantPermissionDto>();
    for (PlantRolePlantPermission plantRolePlantPermission : plantRolePlantPermissionList) {
      PlantRolePlantPermissionDto plantRolePlantPermissionDto = new PlantRolePlantPermissionDto();
      plantRolePlantPermissionDto.setId(plantRolePlantPermission.getId());
      plantRolePlantPermissionDto
          .setPlantPermission(plantRolePlantPermission.getPlantPermission().getName());
      plantRolePlantPermissionDto.setStatus(plantRolePlantPermission.isStatus());
      plantRolePlantPermissionDto.setPlantRoleId(plantRoleId);
      plantRolePlantPermissionDto
          .setPermission(plantRolePlantPermission.getPlantPermission().getPermission().getName());
      plantRolePlantPermissionDtoList.add(plantRolePlantPermissionDto);
    }
    return plantRolePlantPermissionDtoList;
  }

  @Transactional(readOnly = true)
  public boolean isPlantRoleIdExist(Long plantRoleId) {
    return plantRolePlantPermissionRepository.existsByPlantRoleId(plantRoleId);
  }

  @Transactional(readOnly = true)
  public List<RolePermissionResponseDto> getPlantRolePermissionWithModuleByRoleId(
      Long plantRoleId) {
    System.out.println("plantRoleID " + plantRoleId);
    List<RolePermissionResponseDto> PermissionResponseDtolist =
        new ArrayList<RolePermissionResponseDto>();
    mainModuleRepository.findAll().forEach(main -> {
      RolePermissionResponseDto rolePermissionResponseDto = new RolePermissionResponseDto();
      rolePermissionResponseDto.setMainModule(main.getName());
      boolean mainStatus = false;
      List<SubModuleRolePermissionDto> SubModuleRolePermissionDtoList =
          new ArrayList<SubModuleRolePermissionDto>();
      List<SubModule> subModuleList = subModuleRepository.findByMainModuleId(main.getId());
      for (SubModule sub : subModuleList) {
        SubModuleRolePermissionDto subModuleRolePermissionDto = new SubModuleRolePermissionDto();
        subModuleRolePermissionDto.setSubModule(sub.getName());
        boolean subStatus = false;
        List<RolePermissionRequestDto> rolePermissionDtoList =
            new ArrayList<RolePermissionRequestDto>();
        List<PlantRolePlantPermission> PermissionList = plantRolePlantPermissionRepository
            .findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(plantRoleId, sub.getId());
        for (PlantRolePlantPermission permission : PermissionList) {
          RolePermissionRequestDto rolePermissionRequestDto = new RolePermissionRequestDto();
          rolePermissionRequestDto.setPermissionId(permission.getId());
          rolePermissionRequestDto.setRoleId(plantRoleId);
          rolePermissionRequestDto.setStatus(permission.isStatus());
          if (permission.isStatus()) {
            subStatus = true;
          }
          rolePermissionDtoList.add(rolePermissionRequestDto);
        }
        subModuleRolePermissionDto.setStatus(subStatus);
        subModuleRolePermissionDto.setRolePermissions(rolePermissionDtoList);
        SubModuleRolePermissionDtoList.add(subModuleRolePermissionDto);
        if (subStatus) {
          mainStatus = true;
        }
      }
      rolePermissionResponseDto.setStatus(mainStatus);
      rolePermissionResponseDto.setSubModules(SubModuleRolePermissionDtoList);
      PermissionResponseDtolist.add(rolePermissionResponseDto);
    });
    return PermissionResponseDtolist;
  }

  @Transactional(readOnly = true)
  public PlantRolePlantPermission findByPlantRoleIdAndPlantPermissionId(Long plantRoleId,
      Long plantPermissionId) {
    return plantRolePlantPermissionRepository.findByPlantRoleIdAndPlantPermissionId(plantRoleId,
        plantPermissionId);
  }

  @Transactional
  public void saveRolePermission(PlantRolePlantPermission plantRolePlantPermission) {
    plantRolePlantPermissionRepository.save(plantRolePlantPermission);
  }
}
