package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.repositories.ParameterEquationElementRepository;

@Service
public class ParameterEquationElementServiceImpl implements ParameterEquationElementService {
  @Autowired
  private ParameterEquationElementRepository parameterEquationElementRepository;

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(Long parameterEquationId, Long testParameterId) {
    return parameterEquationElementRepository
        .existsByParameterEquationIdAndTestParameterId(parameterEquationId, testParameterId);
  }

  @Transactional(readOnly = true)
  public List<ParameterEquationElement> getAllParameterEquationElements() {
    return parameterEquationElementRepository.findAll();
  }

  @Transactional(readOnly = true)
  public ParameterEquationElement getByParameterEquationElement(Long id) {
    return parameterEquationElementRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isExistsById(Long id) {
    return parameterEquationElementRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteParameterEquationElement(Long id) {
    parameterEquationElementRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<ParameterEquationElement> getByTestParameterId(Long testParameterId) {
    return parameterEquationElementRepository.findByTestParameterId(testParameterId);
  }

  @Transactional(readOnly = true)
  public List<ParameterEquationElement> getByParameterEquationId(Long parameterEquationId) {
    return parameterEquationElementRepository.findByParameterEquationId(parameterEquationId);
  }

  @Transactional(readOnly = true)
  public List<ParameterEquationElement> getByTestParameter(Long testParameterId) {
    return parameterEquationElementRepository
        .findByParameterEquationTestParameterId(testParameterId);
  }
}
