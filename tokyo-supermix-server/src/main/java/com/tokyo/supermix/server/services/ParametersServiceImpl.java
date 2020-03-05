package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tokyo.supermix.data.entities.Parameter;
import com.tokyo.supermix.data.repositories.ParameterRepository;

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
  public boolean isAbbreviationExist(String abbreviation) {
    return parameterRepository.existsByAbbreviation(abbreviation);
  }

  @Override
  public boolean isUpdatedAbbreviationExist(Long id, String abbreviation) {
    if ((!getParameterById(id).getAbbreviation().equalsIgnoreCase(abbreviation))
        && (isAbbreviationExist(abbreviation))) {
      return true;
    }
    return false;
  }

}