package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.repositories.EquationRepository;

@Service
public class EquationServiceImpl implements EquationService {
  @Autowired
  private EquationRepository equationRepository;

  @Transactional
  public void saveEquation(Equation equation) {
    equationRepository.save(equation);
  }

  @Transactional(readOnly = true)
  public List<Equation> getAllEquations() {
    return equationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isEquationExist(Long id) {
    return equationRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public Equation getEquationById(Long id) {
    return equationRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteEquation(Long id) {
    equationRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isFormulaExists(String formula) {
    if (equationRepository.existsByFormula(formula)) {
      return true;
    }
    return false;
  }
}
