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
}
