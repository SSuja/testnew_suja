package com.tokyo.supermix.data.repositories.privilege;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tokyo.supermix.data.entities.privilege.UserPlantPermission;

@Repository
public interface UserPlantPermissionRepository extends JpaRepository<UserPlantPermission, Long>{
  public List<UserPlantPermission> findByUserId(Long userId);
  public boolean existsByUserId(Long userId);
  public List<UserPlantPermission>findByUserIdAndPlantPermissionPermissionSubModuleId(Long userId,Long subModuleId);
  public UserPlantPermission findByUserIdAndPlantPermissionId(Long userId, Long plantPermissionId);
//  public UserPlantPermission findByUserIdAndPlantPermissionPermissionName(Long userId, String PermissionnName);
  public List<UserPlantPermission> findByUserIdAndPlantPermissionPermissionNameAndStatus(
      Long userId, String PermissionnName, Boolean status);
}
