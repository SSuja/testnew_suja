package com.tokyo.supermix.server.services.privilege;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;
import com.tokyo.supermix.data.dto.privilege.PlantRoleResponseDto;
import com.tokyo.supermix.data.entities.Plant;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.data.entities.privilege.QPlantRole;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.PlantRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;
import com.tokyo.supermix.data.repositories.privilege.PlantRoleRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class PlantRoleServiceImpl implements PlantRoleService {
  @Autowired
  private PlantRoleRepository plantRoleRepository;
  @Autowired
  private PlantRepository plantRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  Mapper mapper;

  public PlantRole savePlantRole(String plantCode, Long roleId) {
    Plant plant = plantRepository.getOne(plantCode);
    Role role = roleRepository.getOne(roleId);
    PlantRole plantRole = new PlantRole();
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

  @Transactional
  public List<PlantRole> getPlantRoleByPlantCode(String plantCode, Pageable pageable) {
    return plantRoleRepository.findAllByPlantCode(plantCode, pageable);
  }

  @Transactional(readOnly = true)
  public Long getCountPlantRoleByPlantCode(String plantCode) {
    return plantRoleRepository.countByPlantCode(plantCode);
  }

  @Transactional(readOnly = true)
  public Long getCountPlantRole() {
    return plantRoleRepository.count();
  }

  @Transactional(readOnly = true)
  public List<PlantRoleDto> searchPlantRole(BooleanBuilder booleanBuilder, String roleName,
      String name, String plantCode, String plantName, Pageable pageable, Pagination pagination) {
    if (roleName != null && !roleName.isEmpty()) {
      booleanBuilder.and(QPlantRole.plantRole.role.name.contains(roleName));
    }
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QPlantRole.plantRole.name.contains(name));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QPlantRole.plantRole.plant.code.contains(plantName));
    }

    if (plantCode != null && !plantCode.isEmpty() && !(plantCode.equalsIgnoreCase("ADMIN"))) {
      booleanBuilder.and(QPlantRole.plantRole.plant.code.contains(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<PlantRole>) plantRoleRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(plantRoleRepository.findAll(booleanBuilder, pageable).toList(),
        PlantRoleDto.class);
  }

  @Transactional(readOnly = true)
  public List<PlantRole> getAllPlantRole(Pageable pageable) {
    return plantRoleRepository.findAll(pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<PlantRoleResponseDto> searchPlantRoleWithPagination(BooleanBuilder booleanBuilder,
      String name, String plantCode, String plantName, Pageable pageable, Pagination pagination) {
    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QPlantRole.plantRole.role.name.contains(name));
    }
    if (plantName != null && !plantName.isEmpty()) {
      booleanBuilder.and(QPlantRole.plantRole.plant.code.contains(plantName));
    }
    if (plantCode != null && !plantCode.isEmpty() && !(plantCode.equalsIgnoreCase("ADMIN"))) {
      booleanBuilder.and(QPlantRole.plantRole.plant.code.contains(plantCode));
    }
    pagination.setTotalRecords(
        ((Collection<PlantRole>) plantRoleRepository.findAll(booleanBuilder)).stream().count());
    return mapper.map(plantRoleRepository.findAll(booleanBuilder, pageable).toList(),
        PlantRoleResponseDto.class);
  }
}
