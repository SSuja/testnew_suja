package com.tokyo.supermix.server.services;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.privilege.PlantAccessLevelRequestDto;
import com.tokyo.supermix.data.entities.privilege.PlantAccessLevel;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.repositories.privilege.PlantAccessLevelRepository;

@Service
public class PlantAccessLevelServiceImpl implements PlantAccessLevelService {
  @Autowired
  private PlantAccessLevelRepository plantAccessLevelRepository;

  @Transactional
  public void savePlantRolePlantCode(PlantAccessLevel plantAccessLevel) {
    plantAccessLevelRepository.save(plantAccessLevel);
  }

  @Transactional(readOnly = true)
  public boolean existsByPlantCodeAndPlantRoleId(String plantCode, Long plantRoleId) {
    return plantAccessLevelRepository.existsByPlantCodeAndPlantRoleId(plantCode, plantRoleId);
  }

  @Transactional(readOnly = true)
  public boolean existsByPlantCodeAndStatus(String plantCode, boolean status) {
    return plantAccessLevelRepository.existsByPlantCodeAndStatus(plantCode, status);
  }

  @Transactional(readOnly = true)
  public List<PlantAccessLevel> getPlantRolesByPlantCodeAndStatus(String plantCode,
      boolean status) {
    return plantAccessLevelRepository.findByPlantCodeAndStatus(plantCode, status);
  }

  @Transactional
  public void statusUpdate(PlantAccessLevelRequestDto plantAccessLevelRequestDto) {
    PlantAccessLevel plantAccessLevel =
        plantAccessLevelRepository.findByPlantCodeAndPlantRoleId(plantAccessLevelRequestDto.getPlantCode(),plantAccessLevelRequestDto.getPlantRoleId());
    plantAccessLevel.setStatus(plantAccessLevelRequestDto.isStatus());
    plantAccessLevelRepository.save(plantAccessLevel);
  }

  @Override
  public void createPlantAccessLevel(PlantRole plantRole) {
    PlantAccessLevel plantAccessLevel =new PlantAccessLevel();
    plantAccessLevel.setPlant(plantRole.getPlant());
    plantAccessLevel.setPlantRole(plantRole);
    plantAccessLevel.setStatus(true);
    plantAccessLevelRepository.save(plantAccessLevel);
  }

  
}
