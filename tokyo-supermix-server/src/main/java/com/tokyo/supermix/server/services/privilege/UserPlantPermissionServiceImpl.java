package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PlantResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubModulePlantRolePlantPermissionDto;
import com.tokyo.supermix.data.entities.privilege.MainModule;
import com.tokyo.supermix.data.entities.privilege.SubModule;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.privilege.MainModuleRepository;
import com.tokyo.supermix.data.repositories.privilege.SubModuleRepository;
import com.tokyo.supermix.data.repositories.privilege.UserPlantPermissionRepository;

@Service
public class UserPlantPermissionServiceImpl implements UserPlantPermissionService {

  @Autowired
  private UserPlantPermissionRepository userPlantPermissionRepository;
  @Autowired
  private MainModuleRepository mainModuleRepository;
  @Autowired
  private SubModuleRepository subModuleRepository;
  @Autowired
  Mapper mapper;

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionResponseDto> getPlantRolePermissionsByUserId(Long userId) {
    return getMainModulesWithStatusByuserId(mainModuleRepository.findAll(), userId);
  }

  @Override
  public boolean isUserIdExist(Long userId) {
    return userPlantPermissionRepository.existsByUserId(userId);
  }

  private List<PlantRolePlantPermissionResponseDto> getMainModulesWithStatusByuserId(
      List<MainModule> MainModuleList, Long userId) {
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
      boolean status = getSubModulesByUserIdAndReturnMainStatus(subModuleList, userId, main.getId(),
          subModulePlantRolePlantPermissionDtoList, mainStatus);
      plantRolePlantPermissionResponseDto.setStatus(status);
      plantRolePlantPermissionResponseDto.setSubModules(subModulePlantRolePlantPermissionDtoList);
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });
    return plantRolePlantPermissionResponseDtolist;
  }

  private boolean getSubModulesByUserIdAndReturnMainStatus(List<SubModule> subModuleList,
      Long userId, Long mainModuleId,
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
      List<UserPlantPermission> userPlantPermissionList = userPlantPermissionRepository
          .findByUserIdAndPlantPermissionPermissionSubModuleId(userId, sub.getId());
      boolean status = getPlantPermissionsAndReturnSubModuleStatus(userPlantPermissionList,
          subStatus, userId, sub.getId(), mainModuleId, rolePermissionDtoList);
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
      List<UserPlantPermission> userPlantPermissionList, boolean subStatus, Long userId,
      Long subModuleId, Long mainModuleId,
      List<PlantRolePlantPermissionRequestDto> rolePermissionDtoList) {
    for (UserPlantPermission permission : userPlantPermissionList) {
      PlantRolePlantPermissionRequestDto plantRolePlantPermissionRequestDto =
          new PlantRolePlantPermissionRequestDto();
      plantRolePlantPermissionRequestDto
          .setPermissionName(permission.getPlantPermission().getPermission().getName());
      plantRolePlantPermissionRequestDto.setPlantPermissionId(permission.getId());
      plantRolePlantPermissionRequestDto.setStatus(false);
      plantRolePlantPermissionRequestDto.setSubModuleId(subModuleId);
      plantRolePlantPermissionRequestDto.setMainModuleId(mainModuleId);
      if (permission.getStatus()) {
        subStatus = true;
      }
      rolePermissionDtoList.add(plantRolePlantPermissionRequestDto);
    }
    return subStatus;
  }

  @Transactional
  public void saveUserPlantPermission(List<UserPlantPermission> userPlantPermissions) {
    for (UserPlantPermission userPlantPermission : userPlantPermissions) {
      UserPlantPermission userPlantPermission2 = userPlantPermissionRepository
          .findByUserIdAndPlantPermissionId(userPlantPermission.getUser().getId(),
              userPlantPermission.getPlantPermission().getId());
      if (userPlantPermission2 != null) {
        userPlantPermission2.setStatus(userPlantPermission.getStatus());
        userPlantPermissionRepository.save(userPlantPermission2);
      } else {
        userPlantPermissionRepository.save(userPlantPermission);
      }
    }
  }

  @Transactional(readOnly = true)
  public List<PlantResponseDto> getByUserIdAndPermissionNameAndStatus(Long userId,
      String PermissionnName, Boolean status) {
    List<UserPlantPermission> userPlantPermissionList = userPlantPermissionRepository
        .findByUserIdAndPlantPermissionPermissionNameAndStatus(userId, PermissionnName, status);
    List<PlantResponseDto> plantResponseDtolist = new ArrayList<PlantResponseDto>();
    userPlantPermissionList.forEach(userPlantPermission -> {
      plantResponseDtolist.add(
          mapper.map(userPlantPermission.getPlantPermission().getPlant(), PlantResponseDto.class));
    });
    return plantResponseDtolist;
  }

  @Override
  public List<PlantRolePlantPermissionResponseDto> getByUserIdAndPlantCodeAndStatus(Long userId,
      String plantCode, boolean status) {
    return getMainModulesWithStatusByuserIdAndPlantCode(mainModuleRepository.findAll(), userId,
        plantCode, status);
  }

  private List<PlantRolePlantPermissionResponseDto> getMainModulesWithStatusByuserIdAndPlantCode(
      List<MainModule> MainModuleList, Long userId, String plantCode, boolean status) {
    List<PlantRolePlantPermissionResponseDto> plantRolePlantPermissionResponseDtolist =
        new ArrayList<PlantRolePlantPermissionResponseDto>();
    MainModuleList.forEach(main -> {
      PlantRolePlantPermissionResponseDto plantRolePlantPermissionResponseDto =
          new PlantRolePlantPermissionResponseDto();
      plantRolePlantPermissionResponseDto.setMainModuleId(main.getId());
      plantRolePlantPermissionResponseDto.setMainModule(main.getName());
      boolean mainStatus = false;
      List<SubModulePlantRolePlantPermissionDto> subModulePlantRolePlantPermissionDtoList =
          new ArrayList<SubModulePlantRolePlantPermissionDto>();
      List<SubModule> subModuleList = subModuleRepository.findByMainModuleId(main.getId());
      boolean status1 = getSubModulesByUserIdAndPlantCodeAndReturnMainStatus(subModuleList, userId,
          main.getId(), plantCode, status, subModulePlantRolePlantPermissionDtoList, mainStatus);
      plantRolePlantPermissionResponseDto.setStatus(status1);
      plantRolePlantPermissionResponseDto.setSubModules(subModulePlantRolePlantPermissionDtoList);
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });
    return plantRolePlantPermissionResponseDtolist;

  }

  private boolean getSubModulesByUserIdAndPlantCodeAndReturnMainStatus(
      List<SubModule> subModuleList, Long userId, Long mainModuleId, String plantCode,
      boolean status,
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
      List<UserPlantPermission> userPlantPermissionList = userPlantPermissionRepository
          .findByUserIdAndPlantPermissionPermissionSubModuleIdAndPlantPermissionPlantCodeAndStatus(
              userId, sub.getId(), plantCode, status);
      boolean status1 =
          getPlantPermissionsByPlantCodeAndReturnSubModuleStatus(userPlantPermissionList, subStatus,
              userId, sub.getId(), mainModuleId, rolePermissionDtoList);
      subModulePlantRolePlantPermissionDto.setStatus(status1);
      subModulePlantRolePlantPermissionDto.setPrivilages(rolePermissionDtoList);
      subModulePlantRolePlantPermissionDtoList.add(subModulePlantRolePlantPermissionDto);
      if (status1) {
        mainStatus = true;
      }
    }
    return mainStatus;
  }

  private boolean getPlantPermissionsByPlantCodeAndReturnSubModuleStatus(
      List<UserPlantPermission> userPlantPermissionList, boolean subStatus, Long userId,
      Long subModuleId, Long mainModuleId,
      List<PlantRolePlantPermissionRequestDto> rolePermissionDtoList) {
    for (UserPlantPermission permission : userPlantPermissionList) {
      PlantRolePlantPermissionRequestDto plantRolePlantPermissionRequestDto =
          new PlantRolePlantPermissionRequestDto();
      plantRolePlantPermissionRequestDto
          .setPermissionName(permission.getPlantPermission().getPermission().getName());
      plantRolePlantPermissionRequestDto.setPlantPermissionId(permission.getId());
      plantRolePlantPermissionRequestDto.setStatus(false);
      plantRolePlantPermissionRequestDto.setSubModuleId(subModuleId);
      plantRolePlantPermissionRequestDto.setMainModuleId(mainModuleId);
      if (permission.getStatus()) {
        subStatus = true;
      }
      rolePermissionDtoList.add(plantRolePlantPermissionRequestDto);
    }
    return subStatus;
  }
}


