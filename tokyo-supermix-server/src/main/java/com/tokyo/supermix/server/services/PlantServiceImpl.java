package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.entities.privilege.PlantAccessLevel;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantAccessLevelRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantPermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantRoleRepository;
import com.tokyo.supermix.server.services.privilege.PlantPermissionService;
import com.tokyo.supermix.server.services.privilege.PlantRolePlantPermissionServices;

@Service
public class PlantServiceImpl implements PlantService {
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private PlantRoleRepository plantRoleRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private PlantPermissionRepository plantPermissionRepository;
  @Autowired
  private PlantPermissionService plantPermissionService;
  @Autowired
  private PlantRolePlantPermissionServices plantRolePlantPermissionServices;
  @Autowired
  private PlantAccessLevelRepository plantAccessLevelRepository;

  @Transactional
  public void savePlant(Plant plant) {
    PlantRole plantRole = new PlantRole();
    Plant plantObj = plantRepository.save(plant);
    plantRole.setPlant(plantObj);
    Role role = roleRepository.findById(1l).get();
    plantRole.setRole(role);
    plantRole.setName(plant.getName().toUpperCase() + "_" + role.getName());
    PlantRole plantRoleObj = plantRoleRepository.save(plantRole);
    plantPermissionService.savePlantPermission(plantObj);
    List<PlantPermission> plantPermissionList = plantPermissionRepository.findAll();
    plantPermissionList.forEach(plantpermission -> {
      PlantRolePlantPermission plantRolePlantPermission = new PlantRolePlantPermission();
      plantRolePlantPermission.setPlantRole(plantRoleObj);
      plantRolePlantPermission.setStatus(true);
      plantRolePlantPermission.setPlantPermission(plantpermission);
      plantRolePlantPermissionServices.savePlantRolePlantPermission(plantRolePlantPermission);
    });
    PlantAccessLevel plantAccessLevel= new PlantAccessLevel();
    plantAccessLevel.setPlant(plant);
    plantAccessLevel.setPlantRole(plantRoleObj);
    plantAccessLevel.setStatus(true);
    plantAccessLevelRepository.save(plantAccessLevel);
  }

  @Transactional(readOnly = true)
  public boolean isPlantNameExist(String plant) {
    return plantRepository.existsByName(plant);
  }

  @Transactional(readOnly = true)
  public List<Plant> getAllPlants() {
    return plantRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isPlantExist(String code) {
    return plantRepository.existsByCode(code);
  }

  @Transactional(readOnly = true)
  public Plant getPlantByCode(String code) {
    return plantRepository.findPlantByCode(code);
  }

  public boolean isUpdatedPlantNameExist(String code, String plantName) {
    if ((!getPlantByCode(code).getName().equalsIgnoreCase(plantName))
        && (isPlantNameExist(plantName))) {
      return true;
    }
    return false;
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deletePlant(String code) {
    plantRepository.deleteById(code);
  }

  @Transactional(readOnly = true)
  public Page<Plant> searchPlant(Predicate predicate, int size, int page) {
    return plantRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "code")));
  }

}
