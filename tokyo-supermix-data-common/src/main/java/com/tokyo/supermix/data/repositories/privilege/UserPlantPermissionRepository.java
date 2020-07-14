package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;

@Repository
public interface UserPlantPermissionRepository extends JpaRepository<UserPlantPermission, Long> {
  public List<UserPlantPermission> findByUserId(Long userId);

  public boolean existsByUserId(Long userId);

  public List<UserPlantPermission> findByUserIdAndPlantPermissionPermissionSubModuleId(Long userId,
      Long subModuleId);

  public UserPlantPermission findByUserIdAndPlantPermissionId(Long userId, Long plantPermissionId);

  public List<UserPlantPermission> findByUserIdAndPlantPermissionPermissionNameAndStatus(
      Long userId, String PermissionnName, Boolean status);

  public List<UserPlantPermission> findByUserIdAndPlantPermissionPlantCodeAndStatus(Long userId,
      String plantCode, Boolean status);

  public List<UserPlantPermission> findByUserIdAndPlantPermissionPermissionSubModuleIdAndPlantPermissionPlantCodeAndStatus(
      Long userId, Long subModuleId, String plantCode, boolean status);
}
