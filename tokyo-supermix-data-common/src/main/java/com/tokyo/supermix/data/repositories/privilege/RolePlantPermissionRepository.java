package com.tokyo.supermix.data.repositories.privilege;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;

public interface RolePlantPermissionRepository extends JpaRepository<PlantRolePlantPermission, Long>{

}
