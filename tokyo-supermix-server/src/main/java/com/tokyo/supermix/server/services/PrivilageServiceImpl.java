package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.auth.PermissionResponseDto;
import com.tokyo.supermix.data.entities.auth.Permission;
import com.tokyo.supermix.data.entities.auth.Role;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.auth.RoleRepository;

@Service
public class PrivilageServiceImpl implements PrivilageService {
  @Autowired
  PermissionRepository permissionRepository;
  @Autowired
  RoleRepository roleRepository;

  @Transactional
  public List<PermissionResponseDto> getPermission(Long roleId) {
    List<PermissionResponseDto> permissionResponseDtoList = new ArrayList<PermissionResponseDto>();
    Role role = roleRepository.getOne(roleId);
    List<Permission> permissionList = permissionRepository.findAll();
    List<Permission> truelist = new ArrayList<>();
    List<Permission> rolePermissionList =
        roleRepository.findByRoleName(role.getRoleName()).get().getPermissions();

    for (Permission permission : permissionList) {

      for (Permission rolePermission : rolePermissionList) {
        if (permission.getId() == rolePermission.getId()) {
          PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
          permissionResponseDto.setId(rolePermission.getId());
          permissionResponseDto.setName(rolePermission.getName());
          permissionResponseDto.setStatus(true);
          permissionResponseDtoList.add(permissionResponseDto);
          truelist.add(permission);
          break;
        }
      }
    }
    permissionList.removeAll(truelist);
    permissionList.forEach(per -> {
      PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
      permissionResponseDto.setId(per.getId());
      permissionResponseDto.setName(per.getName());
      permissionResponseDto.setStatus(false);
      permissionResponseDtoList.add(permissionResponseDto);
    });
    return permissionResponseDtoList;
  }


}
