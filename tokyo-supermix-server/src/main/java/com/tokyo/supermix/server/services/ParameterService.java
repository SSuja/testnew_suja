package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.ParameterDto;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.ParameterType;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

public interface ParameterService {
  public List<Parameter> getAllParameters();

  public Parameter saveParameter(Parameter parameter);

  public boolean isParameterExist(Long id);

  public void deleteParameter(Long id);

  public Parameter getParameterById(Long id);

  public boolean isNameExist(String name);

  public boolean isUpdatedNameExist(Long id, String name);

  public List<Parameter> getParameterByParameterType(ParameterType parameterType);

  public boolean isParameterTypeExists(ParameterType parameterType);

  public List<Parameter> getAllParametersByDecending();

  public boolean isParameterNameAndParameterTypeExists(String name, ParameterType parameterType);

  public boolean isExistsByParameterNameAndParameterTypeAndParameterDataType(String name,
      ParameterType parameterType, ParameterDataType parameterDataType);

  public List<Parameter> getAllParametersByDecending(Pageable pageable);

  public Long getCountParameters();

  public List<Parameter> searchParameters(String name, ParameterType parameterType,
      ParameterDataType parameterDataType, BooleanBuilder booleanBuilder, int page, int size,
      Pageable pageable, Pagination pagination);

  public List<Parameter> getAllParametersAndParameterTypeByDecending(ParameterType parameterType,
      Pageable pageable);

  public Long getCountParametersByType(ParameterType parameterType);

  public List<Parameter> searchCommonParameters(String name, ParameterType parameterType,
      BooleanBuilder booleanBuilder);

  public boolean dependedParameter(ParameterDto parameterDto);
}
