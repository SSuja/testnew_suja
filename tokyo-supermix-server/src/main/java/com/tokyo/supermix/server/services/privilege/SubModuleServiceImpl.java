package com.tokyo.supermix.server.services.privilege;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;
import com.tokyo.supermix.data.entities.privilege.SubModule;
import com.tokyo.supermix.data.repositories.privilege.SubModuleRepository;

@Service
public class SubModuleServiceImpl implements SubModuleService {
  @Autowired
  private SubModuleRepository subModuleRepository;

  @Transactional(readOnly = true)
  public List<PermissionDto> getSubModulesByMainModule(Long mainModuleId) {
    List<SubModule> subModules = subModuleRepository.findByMainModuleId(mainModuleId);
    List<PermissionDto> permissionDtolist = new ArrayList<PermissionDto>();
    for (SubModule subModule : subModules) {
      PermissionDto permissionDto = new PermissionDto();
      permissionDto.setId(subModule.getId());
      permissionDto.setName(subModule.getName());
      permissionDtolist.add(permissionDto);
    }
    return permissionDtolist;
  }
}
