package com.tokyo.supermix.data.repositories.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;
import com.tokyo.supermix.data.enums.RoleType;
import com.tokyo.supermix.data.enums.UserType;

public interface UserPlantRoleRepository extends JpaRepository<UserPlantRole, Long> {
  List<UserPlantRole> findByUserId(Long userId);
  List<UserPlantRole> findByUserIdAndPlantRolePlantCode(Long userId, String plantCode);

  List<UserPlantRole> findByPlantRoleId(Long PlantRoleId);
  List<UserPlantRole> findByPlantRoleIdAndUserUserType(Long PlantRoleId,UserType userType);
  boolean existsByPlantRoleId(Long plantRoleId);
  boolean existsByUserId(Long userId);
  UserPlantRole findByPlantRoleIdAndUserId(Long PlantRoleId,Long userId);
  UserPlantRole findByPlantRolePlantCodeAndUserIdAndRoleType(String plantCode,Long userId , RoleType roleType);
}
