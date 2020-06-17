package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PlantRolePlantPermissionDto;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;
import com.tokyo.supermix.data.repositories.privilege.PlantRolePlantPermissionRepository;

@Service
public class PlantRolePlantPermissionServiceImpl implements PlantRolePlantPermissionServices {
  @Autowired
  private PlantRolePlantPermissionRepository plantRolePlantPermissionRepository;


  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleID(
      Long plantRoleId, Long subModuleId) {

    List<PlantRolePlantPermission> PlantRolePlantPermissionList = plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(plantRoleId, subModuleId);
    List<PlantRolePlantPermissionDto> plantRolePlantPermissionDtolist =
        new ArrayList<PlantRolePlantPermissionDto>();
    PlantRolePlantPermissionList.forEach(permission -> {

      PlantRolePlantPermissionDto plantRolePlantPermissionDto = new PlantRolePlantPermissionDto();
      plantRolePlantPermissionDto.setId(permission.getId());
      plantRolePlantPermissionDto.setPlantPermission(permission.getPlantPermission().getName());
      plantRolePlantPermissionDto.setPlantRoleId(permission.getPlantRole().getId());
      plantRolePlantPermissionDto.setStatus(permission.isStatus());
      plantRolePlantPermissionDto.setPermission(permission.getPlantPermission().getPermission().getName());
      plantRolePlantPermissionDtolist.add(plantRolePlantPermissionDto);
    });

    return plantRolePlantPermissionDtolist;
  }

  @Transactional(readOnly = true)
  public List<PlantRolePlantPermissionDto> getByPlantRoleIdAndStatus(Long plantRoleId,
      Boolean status) {
    List<PlantRolePlantPermission> PlantRolePlantPermissionList =
        plantRolePlantPermissionRepository.findByPlantRoleIdAndStatus(plantRoleId, status);

    List<PlantRolePlantPermissionDto> plantRolePlantPermissionDtolist =
        new ArrayList<PlantRolePlantPermissionDto>();
    PlantRolePlantPermissionList.forEach(permission -> {

      PlantRolePlantPermissionDto plantRolePlantPermissionDto = new PlantRolePlantPermissionDto();
      plantRolePlantPermissionDto.setId(permission.getId());
      plantRolePlantPermissionDto.setPlantPermission(permission.getPlantPermission().getName());
      plantRolePlantPermissionDto.setPlantRoleId(permission.getPlantRole().getId());
      plantRolePlantPermissionDto.setStatus(permission.isStatus());
      plantRolePlantPermissionDto.setPermission(permission.getPlantPermission().getPermission().getName());
      plantRolePlantPermissionDtolist.add(plantRolePlantPermissionDto);
    });

    return plantRolePlantPermissionDtolist;
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
  public List<PlantRolePlantPermissionDto> getRolePlantPermissionsByPlantRoleIdAndSubModuleIDAndStatus(
      Long plantRoleId, Long subModuleId, Boolean status) {
    List<PlantRolePlantPermission> PlantRolePlantPermissionList = plantRolePlantPermissionRepository
        .findByPlantRoleIdAndPlantPermissionPermissionSubModuleIdAndStatus(plantRoleId, subModuleId, status);
    List<PlantRolePlantPermissionDto> plantRolePlantPermissionDtolist =
        new ArrayList<PlantRolePlantPermissionDto>();
    PlantRolePlantPermissionList.forEach(permission -> {

      PlantRolePlantPermissionDto plantRolePlantPermissionDto = new PlantRolePlantPermissionDto();
      plantRolePlantPermissionDto.setId(permission.getId());
      plantRolePlantPermissionDto.setPlantPermission(permission.getPlantPermission().getName());
      plantRolePlantPermissionDto.setPlantRoleId(permission.getPlantRole().getId());
      plantRolePlantPermissionDto.setStatus(permission.isStatus());
      plantRolePlantPermissionDto.setPermission(permission.getPlantPermission().getPermission().getName());
      plantRolePlantPermissionDtolist.add(plantRolePlantPermissionDto);
    });

    return plantRolePlantPermissionDtolist; 
       
  }

    @Transactional
    public void savePlantRolePlantPermission(PlantRolePlantPermission plantRolePlantPermission) {
   
      plantRolePlantPermissionRepository.save(plantRolePlantPermission);
  }
}
