package com.tokyo.supermix.server.services.auth;

import java.util.List;
import com.tokyo.supermix.data.dto.auth.RoleDto;
import com.tokyo.supermix.data.dto.auth.UserResponseDto;

public interface UserRoleService {
  List<RoleDto> getRolesByUserId(Long userId);
  List<UserResponseDto> getUsersByRoleId(Long roleId);
  boolean existsByUserId(Long userId);
  boolean existsByRoleId(Long roleId);
  }
