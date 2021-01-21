package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.ParameterType;

public interface ParameterService {
  public List<Parameter> getAllParameters();

  public Parameter saveParameter(Parameter parameter);

  public boolean isParameterExist(Long id);

  public void deleteParameter(Long id);

  public Parameter getParameterById(Long id);

  public boolean isNameExist(String name);

  public boolean isUpdatedNameExist(Long id, String name);

  public Page<Parameter> searchParameter(Predicate predicate, int page, int size);

  public List<Parameter> getParameterByParameterType(ParameterType parameterType);

  public boolean isParameterTypeExists(ParameterType parameterType);

  public List<Parameter> getAllParametersByDecending();

  public boolean isParameterNameAndParameterTypeExists(String name, ParameterType parameterType);

  public boolean isExistsByParameterNameAndParameterTypeAndParameterDataType(String name,
      ParameterType parameterType, ParameterDataType parameterDataType);

  public List<Parameter> getAllParametersByDecending(Pageable pageable);

  public Long getCountParameters();
}
