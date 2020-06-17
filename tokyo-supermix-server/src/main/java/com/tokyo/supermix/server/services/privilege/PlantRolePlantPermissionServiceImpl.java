package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionResponseDto;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;
import com.tokyo.supermix.data.repositories.privilege.PlantRolePlantPermissionRepository;

@Service
public class PlantRolePlantPermissionServiceImpl implements PlantRolePlantPermissionServices {

  @Autowired
  private PlantRolePlantPermissionRepository plantRolePlantPermissionRepository;

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionResponseDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleID(
      Long plantRoleId, Long subModuleId) {

    List<PlantRolePlantPermission> PlantRolePlantPermissionList = plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(plantRoleId, subModuleId);
    List<PlantRolePlantPermissionResponseDto> plantRolePlantPermissionResponseDtolist =
        new ArrayList<PlantRolePlantPermissionResponseDto>();
    PlantRolePlantPermissionList.forEach(permission -> {
      PlantRolePlantPermissionResponseDto plantRolePlantPermissionResponseDto =
          new PlantRolePlantPermissionResponseDto();
      plantRolePlantPermissionResponseDto.setId(permission.getId());
      plantRolePlantPermissionResponseDto.setName(permission.getPlantPermission().getName());
      plantRolePlantPermissionResponseDto.setPlantRoleId(permission.getPlantRole().getId());;
      plantRolePlantPermissionResponseDto.setStatus(permission.isStatus());
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });

    return plantRolePlantPermissionResponseDtolist;
  }

  @Override
  public List<PlantRolePlantPermissionResponseDto> getByPlantRoleIdAndStatus(Long plantRoleId,
      Boolean status) {
    List<PlantRolePlantPermission> PlantRolePlantPermissionList =
        plantRolePlantPermissionRepository.findByPlantRoleIdAndStatus(plantRoleId, status);
   
    List<PlantRolePlantPermissionResponseDto> plantRolePlantPermissionResponseDtolist =
        new ArrayList<PlantRolePlantPermissionResponseDto>();
    PlantRolePlantPermissionList.forEach(permission -> {

      PlantRolePlantPermissionResponseDto plantRolePlantPermissionResponseDto =
          new PlantRolePlantPermissionResponseDto();
      plantRolePlantPermissionResponseDto.setId(permission.getId());
      plantRolePlantPermissionResponseDto.setName(permission.getPlantPermission().getName());
      plantRolePlantPermissionResponseDto.setPlantRoleId(permission.getPlantRole().getId());;
      plantRolePlantPermissionResponseDto.setStatus(permission.isStatus());
      plantRolePlantPermissionResponseDtolist.add(plantRolePlantPermissionResponseDto);
    });

    return plantRolePlantPermissionResponseDtolist;


  }

}
