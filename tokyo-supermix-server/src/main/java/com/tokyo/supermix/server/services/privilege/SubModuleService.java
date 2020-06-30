package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import com.tokyo.supermix.data.dto.privilege.PermissionDto;

public interface SubModuleService {
  public List<PermissionDto> getSubModulesByMainModule(Long mainModuleId);
}
