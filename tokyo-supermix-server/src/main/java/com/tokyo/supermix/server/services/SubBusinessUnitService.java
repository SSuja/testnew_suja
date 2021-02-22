package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.SubBusinessUnit;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface SubBusinessUnitService {

  public List<SubBusinessUnit> getAllSubBusinessUnits();

  public boolean isSubBusinessUnitExist(Long id);

  public SubBusinessUnit getSubBusinessUnitById(Long id);

  public void deleteSubBusinessUnit(Long id);

  public void saveSubBusinessUnit(SubBusinessUnit subBusinessUnit);

  public boolean isSubBusinessUnitExist(String subBusinessUnit);

  public boolean isUpdatedSubBusinessUnitNameExist(Long id, String subBusinessUnit);

  public List<SubBusinessUnit> searchSubBusinessUnit(BooleanBuilder booleanBuilder, String name,
      String description, Pageable pageable, Pagination pagination);

  public List<SubBusinessUnit> getAllSubBusinessUnits(Pageable pageable);

  public Long getCountsubBusinessUnit();
}
