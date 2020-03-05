package com.tokyo.supermix.server.services;

import java.util.List;

import com.tokyo.supermix.data.entities.Parameter;

public interface ParameterService {
  public List<Parameter> getAllParameters();
  public Parameter saveParameter(Parameter parameter);
  public boolean isParameterExist(Long id);
  public void deleteParameter(Long id);
  public Parameter getParameterById(Long id);
  public boolean isNameExist(String name);
  public boolean isAbbreviationExist(String abbreviation);
  public boolean isUpdatedNameExist(Long id, String name);
  public boolean isUpdatedAbbreviationExist(Long id, String abbreviation);
}