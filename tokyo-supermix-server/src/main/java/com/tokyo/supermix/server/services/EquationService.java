package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Equation;

public interface EquationService {
  public void saveEquation(Equation equation);

  public List<Equation> getAllEquations();

  boolean isEquationExist(Long id);

  public Equation getEquationById(Long id);

  public void deleteEquation(Long id);

  public boolean isUpdatedTestConfigureIdExist(Long id, Long testConfigureId);

  public boolean configurationIdExist(Long testConfigureId);
}
