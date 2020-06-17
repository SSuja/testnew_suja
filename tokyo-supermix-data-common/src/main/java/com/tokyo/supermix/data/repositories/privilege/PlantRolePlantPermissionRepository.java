package com.tokyo.supermix.data.repositories.privilege;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.PlantRolePlantPermission;

public interface PlantRolePlantPermissionRepository
    extends JpaRepository<PlantRolePlantPermission, Long> {
  public List<PlantRolePlantPermission> findByPlantRoleId(Long plantRoleId);

  public boolean existsByPlantRoleId(Long plantRoleId);

  public List<PlantRolePlantPermission> findByPlantRoleIdAndPlantPermissionPermissionSubModuleId(
      Long plantRoleId, Long subModuleid);
}
