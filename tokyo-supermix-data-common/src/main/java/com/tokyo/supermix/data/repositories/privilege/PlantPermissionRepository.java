package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;

public interface PlantPermissionRepository extends JpaRepository<PlantPermission, Long> {
  public List<PlantPermission> findByPermissionName(String permissionName);

  public List<PlantPermission> findByPlantCode(String plantcode);

  public List<PlantPermission> findByPlantCodeAndPermissionSubModuleIdAndPermissionSubModuleMainModuleId(
      String plantCode, Long subModuleId, Long mainModuleId);

  public boolean existsByPermissionName(String permissionName);

  public List<PlantPermission> findByPermissionSubModuleId(Long id);
  
  public List<PlantPermission> findByPlantCodeAndPermissionSubModuleId(String plantCode,Long id);
}
