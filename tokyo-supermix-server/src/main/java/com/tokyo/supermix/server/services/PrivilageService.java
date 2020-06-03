package com.tokyo.supermix.server.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.PrivilageRequestDto;
import com.tokyo.supermix.data.entities.auth.Permission;
import com.tokyo.supermix.data.entities.auth.Role;

@Service
public interface PrivilageService {

  public List<Permission> getBySubRouteName(String subRouteName);

  public List<Permission> getBySubRouteMainRouteName(String mainRouteName);

  public Role save(Role role);

  public Optional<Role> findByRoleName(String roleName);

  public void deleteRole(Long id);

  public void addDeleteRolePermissions(List<PrivilageRequestDto> privilageRequestDtos);

}
