package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.RolePermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubModuleRolePermissionDto;
import com.tokyo.supermix.data.entities.privilege.Permission;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.entities.privilege.SubModule;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.MainModuleRepository;
import com.tokyo.supermix.data.repositories.privilege.RolePermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.SubModuleRepository;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
  @Autowired
  private RolePermissionRepository rolePermissionRepository;
  @Autowired
  private PermissionRepository permissionRepository;
  @Autowired
  private MainModuleRepository mainModuleRepository;
  @Autowired
  private SubModuleRepository subModuleRepository;
  @Autowired
  private MainModuleService mainModuleService;

  @Transactional
  public void saveRolePermission(RolePermission rolePermission) {
    rolePermissionRepository.save(rolePermission);
  }

  @Transactional
  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId) {
    return rolePermissionRepository.findByRoleIdAndPermissionId(permissionId, roleId);
  }

  @Transactional(readOnly = true)
  public List<RolePermissionRequestDto> getRolePermissionByRole(Long roleId) {
    List<RolePermissionRequestDto> rolePermissionDtoList = new ArrayList<RolePermissionRequestDto>();
    List<Permission> permissionList = permissionRepository.findByRolePermissionRoleId(roleId);
    return mainModuleService.setRolePermissionByRole(roleId, permissionList, rolePermissionDtoList);
  }

  @Transactional(readOnly = true)
  public List<RolePermission> getRolePermissionByStatus(boolean status) {
    return rolePermissionRepository.findByStatus(status);
  }


  public List<RolePermissionResponseDto> getRolePermissionWithModuleByRoleId(Long roleId) {
    List<RolePermissionResponseDto> PermissionResponseDtolist = new ArrayList<RolePermissionResponseDto>();
    mainModuleRepository.findAll().forEach(main -> {
      RolePermissionResponseDto rolePermissionResponseDto = new RolePermissionResponseDto();
      rolePermissionResponseDto.setMainModule(main.getName());
      boolean mainStatus = false;
      List<SubModuleRolePermissionDto> SubModuleRolePermissionDtoList =
          new ArrayList<SubModuleRolePermissionDto>();
      List<SubModule> subModuleList = subModuleRepository.findByMainModuleId(main.getId());
      for (SubModule sub : subModuleList) {
        SubModuleRolePermissionDto subModuleRolePermissionDto = new SubModuleRolePermissionDto();
        subModuleRolePermissionDto.setSubModule(sub.getName());
        boolean subStatus = false;
        List<RolePermissionRequestDto> rolePermissionDtoList = new ArrayList<RolePermissionRequestDto>();
        List<Permission> PermissionList =
            permissionRepository.findByRolePermissionRoleIdAndSubModuleId(roleId, sub.getId());
        for (Permission permission : PermissionList) {
          RolePermissionRequestDto rolePermissionRequestDto = new RolePermissionRequestDto();
          rolePermissionRequestDto.setPermissionId(permission.getId());
          rolePermissionRequestDto.setRoleId(roleId);
          boolean rolePermissionStatus = rolePermissionRepository
              .findByRoleIdAndPermissionId(roleId, permission.getId()).isStatus();
          if (rolePermissionStatus) {
            subStatus = true;
          }
          rolePermissionRequestDto.setStatus(rolePermissionStatus);
          rolePermissionDtoList.add(rolePermissionRequestDto);
        }
        subModuleRolePermissionDto.setStatus(subStatus);
        subModuleRolePermissionDto.setRolePermissions(rolePermissionDtoList);
        SubModuleRolePermissionDtoList.add(subModuleRolePermissionDto);
        if (subStatus) {
          mainStatus = true;
        }
      }
      rolePermissionResponseDto.setStatus(mainStatus);
      rolePermissionResponseDto.setSubModules(SubModuleRolePermissionDtoList);
      PermissionResponseDtolist.add(rolePermissionResponseDto);
    });
    return PermissionResponseDtolist;
  }
}
