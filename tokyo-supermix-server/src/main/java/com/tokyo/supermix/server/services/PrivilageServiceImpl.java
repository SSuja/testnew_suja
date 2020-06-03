package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.PrivilageRequestDto;
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
