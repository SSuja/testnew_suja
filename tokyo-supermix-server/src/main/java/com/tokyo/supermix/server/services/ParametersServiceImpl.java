package com.tokyo.supermix.server.services;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.entities.QParameter;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.enums.ParameterType;
import com.tokyo.supermix.data.repositories.ParameterRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class ParametersServiceImpl implements ParameterService {
  @Autowired
  private ParameterRepository parameterRepository;

  @Transactional(readOnly = true)
  public List<Parameter> getAllParameters() {
    return parameterRepository.findAll();
  }

  @Transactional
  public Parameter saveParameter(Parameter parameter) {
    return parameterRepository.save(parameter);
  }

  @Transactional(readOnly = true)
  public boolean isParameterExist(Long id) {
    return parameterRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteParameter(Long id) {
    parameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public Parameter getParameterById(Long id) {
    return parameterRepository.findById(id).get();
  }

  public boolean isUpdatedNameExist(Long id, String name) {
    if ((!getParameterById(id).getName().equalsIgnoreCase(name)) && (isNameExist(name))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isNameExist(String name) {
    return parameterRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public List<Parameter> getParameterByParameterType(ParameterType parameterType) {
    return parameterRepository.findByParameterType(parameterType);
  }

  @Transactional(readOnly = true)
  public boolean isParameterTypeExists(ParameterType parameterType) {
    return parameterRepository.existsByParameterType(parameterType);
  }

  @Transactional(readOnly = true)
  public List<Parameter> getAllParametersByDecending() {
    return parameterRepository.findByOrderByIdDesc();
  }

  @Override
  public boolean isParameterNameAndParameterTypeExists(String name, ParameterType parameterType) {
    return parameterRepository.existsByNameAndParameterType(name, parameterType);
  }

  @Transactional(readOnly = true)
  public Long getCountParameters() {
    return parameterRepository.count();
  }

  @Transactional(readOnly = true)
  public List<Parameter> getAllParametersByDecending(Pageable pageable) {
    return parameterRepository.findAllByOrderByIdDesc(pageable);
  }

  @Transactional(readOnly = true)
  public boolean isExistsByParameterNameAndParameterTypeAndParameterDataType(String name,
      ParameterType parameterType, ParameterDataType parameterDataType) {
    return parameterRepository.existsByNameAndParameterTypeAndParameterDataType(name, parameterType,
        parameterDataType);
  }

  @Transactional(readOnly = true)
  public List<Parameter> searchParameters(String name, ParameterType parameterType,
      ParameterDataType parameterDataType, BooleanBuilder booleanBuilder, int page, int size,
      Pageable pageable, Pagination pagination) {

    if (name != null && !name.isEmpty()) {
      booleanBuilder.and(QParameter.parameter.name.startsWithIgnoreCase(name));
    }
    if (parameterType != null) {
      booleanBuilder.and(QParameter.parameter.parameterType.eq(parameterType));
    }
    if (parameterDataType != null) {
      booleanBuilder.and(QParameter.parameter.parameterDataType.eq(parameterDataType));
    }
    pagination.setTotalRecords(
        ((Collection<Parameter>) parameterRepository.findAll(booleanBuilder)).stream().count());
    return parameterRepository.findAll(booleanBuilder, pageable).toList();
  }
}
