package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.entities.Parameter;

public interface ParameterService {
  public List<Parameter> getAllParameters();

  public Parameter saveParameter(Parameter parameter);

  public boolean isParameterExist(Long id);

  public void deleteParameter(Long id);

  public Parameter getParameterById(Long id);

  public boolean isNameExist(String name);

  public boolean isUpdatedNameExist(Long id, String name);

  public Page<Parameter> searchParameter(Predicate predicate, int page, int size);

}
