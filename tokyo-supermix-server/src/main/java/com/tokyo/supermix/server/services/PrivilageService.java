package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.auth.PermissionResponseDto;
import com.tokyo.supermix.data.entities.auth.Permission;
import com.tokyo.supermix.data.entities.auth.Role;

@Service
public interface PrivilageService {

  public List<Permission> getBySubRouteName(String subRouteName);

  public List<Permission> getBySubRouteMainRouteName(String mainRouteName);
  
  public void savePrivilage(List<Role> roles);
  
  public List<PermissionResponseDto> getPermission(Long roleId);
}
