package com.tokyo.supermix.server.services;

import java.util.List;
import com.tokyo.supermix.data.entities.Equation;

public interface EquationService {
  public boolean isFormulaExist(String formula);

  public void saveEquation(Equation equation);

  public List<Equation> getAllEquations();

  boolean isEquationExist(Long id);

  public Equation getEquationById(Long id);

  public void deleteEquation(Long id);

  public boolean isUpdatedFormulaExist(Long id, String formula);
}
