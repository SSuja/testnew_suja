package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;
import com.tokyo.supermix.data.dto.privilege.SubModulePermissionDto;
import com.tokyo.supermix.data.entities.privilege.Permission;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.auth.PermissionRepository;

@Service
public class PermissionServiceImpl implements PermissionService {
  @Autowired
  private PermissionRepository permissionRepository;
  @Autowired
  private Mapper mapper;

  @Transactional(readOnly = true)
  public List<PermissionDto> getPermissions() {
    List<Permission> permissions = permissionRepository.findAll();
    List<PermissionDto> permissionDtolist = new ArrayList<PermissionDto>();
    for (Permission permission : permissions) {
      PermissionDto permissionDto = new PermissionDto();
      permissionDto.setId(permission.getId());
      permissionDto.setName(permission.getName());
      permissionDtolist.add(permissionDto);
    }
    return permissionDtolist;
  }

  @Transactional(readOnly = true)
  public SubModulePermissionDto getPermissionsBySubModule(String subModule) {
    return getPermissionsSubModuleName(subModule);
  }

  public SubModulePermissionDto getPermissionsSubModuleName(String subModuleName) {
    SubModulePermissionDto subModulePermissionDto = new SubModulePermissionDto();
    List<PermissionDto> PermissionDtoList = new ArrayList<PermissionDto>();
    permissionRepository.findBySubModuleName(subModuleName).forEach(permission -> {
      PermissionDto permissionDto = mapper.map(permission, PermissionDto.class);
      PermissionDtoList.add(permissionDto);
    });
    subModulePermissionDto.setPermissions(PermissionDtoList);
    subModulePermissionDto.setSubModule(subModuleName);
    return subModulePermissionDto;

  }
}
