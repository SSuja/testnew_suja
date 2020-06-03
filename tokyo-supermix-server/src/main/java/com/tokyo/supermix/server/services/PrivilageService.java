package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.PrivilageRequestDto;
import com.tokyo.supermix.data.dto.auth.PermissionResponseDto;

@Service
public interface PrivilageService {

  public void addDeleteRolePermissions(List<PrivilageRequestDto> privilageRequestDtos);
  
  public List<PermissionResponseDto> getPermission(Long roleId);
}

