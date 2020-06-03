package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.PrivilageRequestDto;
import com.tokyo.supermix.data.entities.auth.Permission;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;

@Service
public class PrivilageServiceImpl implements PrivilageService {
  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  PermissionRepository permissionRepository;

  @Override
  public List<Permission> getBySubRouteName(String subRouteName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Permission> getBySubRouteMainRouteName(String mainRouteName) {
    // TODO Auto-generated method stub
    return null;
  }

  public Role save(Role role) {
    return roleRepository.save(role);
    
  }

  @Override
  public Optional<Role> findByRoleName(String roleName) {
    return roleRepository.findByRoleName(roleName);
  }

  @Override
  public void deleteRole(Long id) {
   roleRepository.deleteById(id);
    
  }

  @Transactional
  public void addDeleteRolePermissions(List<PrivilageRequestDto> privilageRequestDtos) {
    List<String> dtoList = new ArrayList<String>();
    for (int i = 0; i < privilageRequestDtos.size(); i++) {
      dtoList.add(privilageRequestDtos.get(i).getRoleName());
    }
    for (int j = 0; j < dtoList.size(); j++) {
      ArrayList<Long> permissionsId = new ArrayList<Long>();
      ArrayList<Long> falsePermissionId = new ArrayList<Long>();
      String roleName = roleRepository.findByRoleName(dtoList.get(j)).get().getRoleName();
      List<Permission> permissions = roleRepository.findByRoleName(roleName).get().getPermissions();
      for (Permission permission : permissions) {
        permissionsId.add(permission.getId());
      }
      for (PrivilageRequestDto privilageRequestDto : privilageRequestDtos) {

        if (privilageRequestDto.getStatus() == true
            && privilageRequestDto.getRoleName() == dtoList.get(j)) {
          permissionsId.add(privilageRequestDto.getPermissionId());
        } else if (privilageRequestDto.getStatus() == false
            && privilageRequestDto.getRoleName() == dtoList.get(j)) {
          privilageRequestDto.getPermissionId();
          falsePermissionId.add(privilageRequestDto.getPermissionId());
        }
      }
      permissionsId.removeAll(falsePermissionId);
      Role role = new Role();
      role.setId(roleRepository.findByRoleName(roleName).get().getId());
      role.setRoleName(roleName);
      List<Permission> permissionList = new ArrayList<Permission>();
      permissionsId.forEach(id -> permissionList.add(permissionRepository.findById(id).get()));
      role.setPermissions(permissionList);
      roleRepository.save(role);
    }   
  }
}
