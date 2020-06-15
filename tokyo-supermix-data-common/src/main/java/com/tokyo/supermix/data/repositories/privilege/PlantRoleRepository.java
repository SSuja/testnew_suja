package com.tokyo.supermix.data.repositories.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.privilege.PlantRole;

@Repository
public interface PlantRoleRepository extends JpaRepository<PlantRole, Long> {
  boolean existsByPlantCodeAndRoleId(String plantCode, Long roleId);
}
