package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;

@Service
public class ParameterEquationServiceImpl implements ParameterEquationService {
  @Autowired
  private ParameterEquationRepository parameterEquationRepository;

  @Transactional
  public List<ParameterEquation> saveParameterEquation(List<ParameterEquation> parameterEquation) {
    return parameterEquationRepository.saveAll(parameterEquation);
  }

  @Transactional(readOnly = true)
  public boolean isEquationIdAndTestParameterId(Long equationId, Long testParameterId) {
    return parameterEquationRepository.existsByEquationIdAndTestParameterId(equationId,
        testParameterId);
  }

  @Transactional(readOnly = true)
  public List<ParameterEquation> getParameterEquations() {
    return parameterEquationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ParameterEquation getParameterEquationById(Long id) {
    return parameterEquationRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isParameterEquationExist(Long id) {
    return parameterEquationRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteParameterEquation(Long id) {
    parameterEquationRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public ParameterEquation getParameterEquationByTestParameter(Long testParameterId) {
    return parameterEquationRepository.findByTestParameterId(testParameterId);
  }

  @Transactional
  public ParameterEquation updateParameterEquation(ParameterEquation parameterEquation) {
    return parameterEquationRepository.save(parameterEquation);
  }
}
