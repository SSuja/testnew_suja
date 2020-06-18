package com.tokyo.supermix.data.repositories.auth;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.auth.UserPlantRole;

public interface UserPlantRoleRepository extends JpaRepository<UserPlantRole, Long> {
  List<UserPlantRole> findRolesByUserId(Long userId);

  List<UserPlantRole> findUsersByPlantRoleId(Long PlantRoleId);

  boolean existsByPlantRoleId(Long plantRoleId);

  boolean existsByUserId(Long userId);

}
