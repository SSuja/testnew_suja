package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.enums.EquationType;
import com.tokyo.supermix.data.repositories.EquationRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;

@Service
public class EquationServiceImpl implements EquationService {
  @Autowired
  private EquationRepository equationRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;

  @Transactional
  public Long saveEquation(Equation equation) {
    equationRepository.save(equation);
    return equation.getId();
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

  @Transactional(readOnly = true)
  public List<Equation> getEquationsByEquationType(EquationType equationType) {
    return equationRepository.findByEquationType(equationType);
  }

  @Transactional(readOnly = true)
  public List<Equation> getEquationsByName(String name) {
    return equationRepository.findByName(name);
  }

  @Transactional(readOnly = true)
  public boolean isNameExists(String name) {
    return equationRepository.existsByName(name);
  }

  @Transactional(readOnly = true)
  public List<Equation> getEquationsByParameterExistsTrue() {
    return equationRepository.findByParameterExistsTrue();
  }

  @Transactional
  public Long updateTestConfigureEquation(Long testConfigureId, Equation equation) {
    saveEquation(equation);
    testConfigureRepository.findById(testConfigureId).get().setEquation(equation);
    return equation.getId();
  }
}
