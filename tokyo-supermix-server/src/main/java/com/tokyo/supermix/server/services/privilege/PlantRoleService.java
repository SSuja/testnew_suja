package com.tokyo.supermix.server.services.privilege;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.privilege.PlantRoleDto;
import com.tokyo.supermix.data.entities.privilege.PlantRole;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface PlantRoleService {
  public PlantRole savePlantRole(String plantCode, Long roleId);

  public boolean existsByPlantCodeAndRoleId(String plantCode, Long roleId);

  public List<PlantRole> getPlantRolesByRoleName(String roleName);

  public List<PlantRole> getAllPlantRole();

  public List<PlantRole> getAllPlantRolesByPlantCode(String plantCode);

  public boolean existsByPlantCode(String plantCode);

  public List<PlantRole> getPlantRoleByPlantCode(String plantCode, Pageable pageable);

  public Long getCountPlantRoleByPlantCode(String plantCode);

  public Long getCountPlantRole();

  public List<PlantRoleDto> searchPlantRole(BooleanBuilder booleanBuilder, String roleName,
      String name, String plantCode,String plantName, Pageable pageable, Pagination pagination);
  
  public List<PlantRole> getAllPlantRole(Pageable pageable);
}
