package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.MaterialStateDto;
import com.tokyo.supermix.data.entities.MaterialState;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface MaterialStateService {
  public MaterialState saveMaterialState(MaterialState materialState);

  public boolean isMaterialStateExist(String materialState);

  public boolean isMaterialStateExist(Long id);

  public List<MaterialState> getAllMaterialStates();

  public MaterialState getMaterialStateById(Long id);

  public boolean isUpdatedMaterialStateExist(Long id, String materialState);

  public void deleteMaterialState(Long id);

  public Long getCountMaterialState();

  public List<MaterialState> getAllMaterialState(Pageable pageable);

  public List<MaterialStateDto> searchDesignation(BooleanBuilder booleanBuilder, String materialState,
      Pageable pageable, Pagination pagination);

}
