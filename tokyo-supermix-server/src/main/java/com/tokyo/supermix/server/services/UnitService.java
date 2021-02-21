package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.Unit;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface UnitService {

  public void saveUnit(Unit unit);

  public boolean isUnitExist(Long id);

  public boolean isUnitExist(String unit);

  public List<Unit> getAllUnits();

  public void deleteUnit(Long id);

  public Unit getUnitById(Long id);

  public boolean isUpdatedUnitExist(Long id, String unit);

  public List<Unit> searchUnit(BooleanBuilder booleanBuilder, String unit, Pageable pageable,
      String plantCode, Pagination pagination);

  public List<Unit> getAllUnits(Pageable pageable);

  public Long getCountUnit();
}
