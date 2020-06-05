package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;
import com.tokyo.supermix.data.dto.privilege.PermissionResponseDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeDto;
import com.tokyo.supermix.data.dto.privilege.PrivilegeResponseDto;
import com.tokyo.supermix.data.dto.privilege.SubRoutePermissionDto;
import com.tokyo.supermix.data.dto.privilege.SubRoutePrivilegeDto;
import com.tokyo.supermix.data.entities.privilege.MainRoute;
import com.tokyo.supermix.data.entities.privilege.RolePermission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.MainRouteRepository;
import com.tokyo.supermix.data.repositories.privilege.RolePermissionRepository;
import com.tokyo.supermix.data.repositories.privilege.SubRouteRepository;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
  @Autowired
  private RolePermissionRepository rolePermissionRepository;
  @Autowired
  private PermissionRepository permissionRepository;
  @Autowired
  private MainRouteRepository mainRouteRepository;
  @Autowired
  private SubRouteRepository subRouteRepository;
  @Autowired
  private Mapper mapper;

  @Transactional
  public void savePrivilege(RolePermission rolePermission) {
    rolePermissionRepository.save(rolePermission);
  }

  @Transactional
  public RolePermission findByRoleIdAndPermissionId(Long permissionId, Long roleId) {
    return rolePermissionRepository.findByRoleIdAndPermissionId(permissionId, roleId);
  }

  private List<PermissionResponseDto> getPermissionsByMainRoute(List<MainRoute> MainRouteList) {
    List<PermissionResponseDto> PermissionResponseDtolist = new ArrayList<PermissionResponseDto>();
    MainRouteList.forEach(main -> {
      PermissionResponseDto permissionResponseDto = new PermissionResponseDto();
      permissionResponseDto.setMainRoute(main.getName());
      List<SubRoutePermissionDto> subRoutePermissionDtoList = new ArrayList<SubRoutePermissionDto>();
      subRouteRepository.findByMainRouteId(main.getId()).forEach(sub -> {
        SubRoutePermissionDto subRoutePermissionDto = new SubRoutePermissionDto();
        subRoutePermissionDto.setSubRoute(sub.getName());
        List<PermissionDto> PermissionDtoList = new ArrayList<PermissionDto>();
        permissionRepository.findBySubRouteId(sub.getId()).forEach(permission -> {
          PermissionDto permissionDto = mapper.map(permission, PermissionDto.class);
          PermissionDtoList.add(permissionDto);
        });
        subRoutePermissionDto.setPermissions(PermissionDtoList);
        subRoutePermissionDtoList.add(subRoutePermissionDto);
      });
      permissionResponseDto.setSubRoutes(subRoutePermissionDtoList);
      PermissionResponseDtolist.add(permissionResponseDto);
    });
    return PermissionResponseDtolist;
  }

  @Override
  public List<PrivilegeResponseDto> getPermissionsByRole(Long roleId) {
    List<PrivilegeResponseDto> PermissionResponseDtolist = new ArrayList<PrivilegeResponseDto>();
    mainRouteRepository.findAll().forEach(main -> {
      PrivilegeResponseDto privilegeResponseDto = new PrivilegeResponseDto();
      privilegeResponseDto.setMainRoute(main.getName());
      List<SubRoutePrivilegeDto> SubRoutePrivilegeDtoList = new ArrayList<SubRoutePrivilegeDto>();
      subRouteRepository.findByMainRouteId(main.getId()).forEach(sub -> {
        SubRoutePrivilegeDto subRoutePrivilegeDto = new SubRoutePrivilegeDto();
        subRoutePrivilegeDto.setSubRoute(sub.getName());
        List<PrivilegeDto> PrivilegeDtoList = new ArrayList<PrivilegeDto>();
        permissionRepository.findByRolePermissionRoleIdAndSubRouteId(roleId,sub.getId()).forEach(permission -> {
          PrivilegeDto privilegeDto = new PrivilegeDto();
          privilegeDto.setId(permission.getId());
          privilegeDto.setPermission(permission.getName());
          privilegeDto.setStatus(
              rolePermissionRepository.findByRoleIdAndPermissionId(roleId, permission.getId()).isStatus());
          PrivilegeDtoList.add(privilegeDto);
        });
        subRoutePrivilegeDto.setPrivileges(PrivilegeDtoList);
        SubRoutePrivilegeDtoList.add(subRoutePrivilegeDto);
      });
      privilegeResponseDto.setSubRoutes(SubRoutePrivilegeDtoList);
      PermissionResponseDtolist.add(privilegeResponseDto);
    });
    return PermissionResponseDtolist;
  }

  @Override
  public List<PermissionResponseDto> getPermissions() {
    return getPermissionsByMainRoute(mainRouteRepository.findAll());
  }


}
