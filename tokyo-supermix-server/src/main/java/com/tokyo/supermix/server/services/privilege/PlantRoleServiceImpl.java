package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantRoleRepository;

@Service
public class PlantRoleServiceImpl implements PlantRoleService {
  @Autowired
  private PlantRoleRepository plantRoleRepository;
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private RoleRepository roleRepository;
  
  @Transactional
  public PlantRole savePlantRole(String plantCode , Long roleId) {
    Plant plant = plantRepository.getOne(plantCode);
    Role role = roleRepository.getOne(roleId);
    PlantRole plantRole =new PlantRole();
    plantRole.setPlant(plant);
    plantRole.setRole(role);
    plantRole.setName(plant.getName().toUpperCase() + "_" + role.getName());
    return plantRoleRepository.save(plantRole);
  }

  @Transactional
  public boolean existsByPlantCodeAndRoleId(String plantCode, Long roleId) {
    return plantRoleRepository.existsByPlantCodeAndRoleId(plantCode, roleId);
  }

  @Transactional(readOnly = true)
  public List<PlantRole> getPlantRolesByRoleName(String roleName) {
    return plantRoleRepository.findByRoleName(roleName);
  }

  @Transactional(readOnly = true)
  public List<PlantRole> getAllPlantRole() {
    return plantRoleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<PlantRole> getAllPlantRolesByPlantCode(String plantCode) {
    return plantRoleRepository.findByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public boolean existsByPlantCode(String plantCode) {
    return plantRoleRepository.existsByPlantCode(plantCode);
  }
}
