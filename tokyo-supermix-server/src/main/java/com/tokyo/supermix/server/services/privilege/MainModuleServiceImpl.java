package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.RolePermissionRequestDto;
import com.tokyo.supermix.data.dto.privilege.SubModulePermissionDto;
import com.tokyo.supermix.data.entities.privilege.MainModule;
import com.tokyo.supermix.data.entities.privilege.Permission;
import com.tokyo.supermix.data.repositories.privilege.MainModuleRepository;
import com.tokyo.supermix.data.repositories.privilege.RolePermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.SubModuleRepository;

@Service
public class MainModuleServiceImpl implements MainModuleService {
  @Autowired
  private MainModuleRepository mainModuleRepository;
  @Autowired
  private SubModuleRepository subModuleRepository;
  @Autowired
  private RolePermissionRepository rolePermissionRepository;
  @Autowired
  private PermissionService permissionService;

  @Transactional(readOnly = true)
  public List<MainModule> getMainModules() {
    return mainModuleRepository.findAll();
  }

  @Transactional(readOnly = true)
  public List<PermissionResponseDto> getModulePermissions() {
    return getPermissionsByMainModule(mainModuleRepository.findAll());
  }

  private List<PermissionResponseDto> getPermissionsByMainModule(List<MainModule> MainModuleList) {
    List<PermissionResponseDto> PermissionResponseDtolist = new ArrayList<PermissionResponseDto>();
    MainModuleList.forEach(main -> {
      PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
      permissionResponseDto.setMainModule(main.getName());
      List<SubModulePermissionDto> subModulePermissionDtoList =
          new ArrayList<SubModulePermissionDto>();
      setPermissiontoSubModule(main, subModulePermissionDtoList);
      permissionResponseDto.setSubModules(subModulePermissionDtoList);
      PermissionResponseDtolist.add(permissionResponseDto);
    });
    return PermissionResponseDtolist;
  }

  private void setPermissiontoSubModule(MainModule main,
      List<SubModulePermissionDto> subModulePermissionDtoList) {
    subModuleRepository.findByMainModuleId(main.getId()).forEach(sub -> {
      SubModulePermissionDto subModulePermissionDto =
          permissionService.getPermissionsSubModuleName(sub.getName());
      subModulePermissionDtoList.add(subModulePermissionDto);
    });
  }

  @Transactional(readOnly = true)
  public MainModule findByMainModuleName(String mainModule) {
    return mainModuleRepository.findByName(mainModule);
  }

  @Transactional(readOnly = true)
  public List<RolePermissionRequestDto> setRolePermissionByRole(Long roleId,
      List<Permission> permissionList, List<RolePermissionRequestDto> rolePermissionDtoList) {
    permissionList.forEach(permission -> {
      RolePermissionRequestDto rolePermissionDto = new RolePermissionRequestDto();
      rolePermissionDto.setPermissionId(permission.getId());
      rolePermissionDto.setRoleId(roleId);
      rolePermissionDto.setStatus(rolePermissionRepository
          .findByRoleIdAndPermissionId(roleId, permission.getId()).isStatus());
      rolePermissionDtoList.add(rolePermissionDto);
    });
    return rolePermissionDtoList;
  }
}
