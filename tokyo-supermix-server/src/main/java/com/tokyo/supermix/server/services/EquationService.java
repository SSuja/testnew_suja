package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.enums.EquationType;

public interface EquationService {
  public Long saveEquation(Equation equation);

  public List<Equation> getAllEquations();

  boolean isEquationExist(Long id);

  public Equation getEquationById(Long id);

  public void deleteEquation(Long id);

  public boolean isFormulaExists(String formula);

  public List<Equation> getEquationsByEquationType(EquationType equationType);
}
