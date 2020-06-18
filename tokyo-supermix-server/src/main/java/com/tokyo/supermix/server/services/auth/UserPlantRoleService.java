package com.tokyo.supermix.server.services.auth;

import java.util.List;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;

public interface UserPlantRoleService {
  List<PlantRoleDto> getRolesByUserId(Long userId);
  List<UserResponseDto> getUsersByPlantRoleId(Long PlantRoleId);
  boolean existsByUserId(Long userId);
  boolean existsByPlantRoleId(Long plantRoleId);
}
