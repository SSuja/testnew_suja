package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tokyo.supermix.data.entities.privilege.PlantPermission;

public interface PlantPermissionRepository extends JpaRepository<PlantPermission, Long> {
  List<PlantPermission> findByPermissionName(String permissionName);
}