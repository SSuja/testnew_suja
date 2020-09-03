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
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.notification.EmailNotification;
import com.tokyo.supermix.security.UserPrincipal;
import com.tokyo.supermix.server.services.privilege.CurrentUserPermissionPlantService;
import com.tokyo.supermix.server.services.privilege.PlantPermissionService;
import com.tokyo.supermix.server.services.privilege.PlantRoleService;
import com.tokyo.supermix.util.privilege.PermissionConstants;

@Service
public class PlantServiceImpl implements PlantService {
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private EmailNotification emailNotification;
  @Autowired
  private CurrentUserPermissionPlantService currentUserPermissionPlantService;
  @Autowired
  private PlantRoleService plantRoleService;
  @Autowired
  private PlantPermissionService plantPermissionService;

  public Plant savePlant(Plant plant) {
    Plant plantObj = plantRepository.save(plant);
    if (plantObj != null) {
      emailNotification.sendPlantCreationEmail(plantObj);
      PlantRole plantRoleObj = plantRoleService.savePlantRole(plant.getCode(), 1L);
      plantRoleService.savePlantRole(plant.getCode(), 2L);
      plantPermissionService.savePlantPermission(plantObj, plantRoleObj);
    }
    return plantRepository.save(plant);
  }

  @Transactional(readOnly = true)
  public boolean isPlantNameExist(String plant) {
    return plantRepository.existsByName(plant);
  }

  @Transactional(readOnly = true)
  public List<Plant> getAllPlants(UserPrincipal currentUser) {
    return plantRepository.findByCodeIn(currentUserPermissionPlantService
        .getPermissionPlantCodeByCurrentUser(currentUser, PermissionConstants.VIEW_PLANT));
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

  @Transactional
  public Plant editPlant(Plant plant) {
    if (!(plantRepository.findById(plant.getCode()).get().getName().equals(plant.getName()))) {
      plantRoleService.getAllPlantRolesByPlantCode(plant.getCode()).forEach(plantRoles -> {
        plantRoles.setName(plant.getName().toUpperCase() + "_" + plantRoles.getRole().getName());
      });
    }
    return plantRepository.save(plant);
  }
}
