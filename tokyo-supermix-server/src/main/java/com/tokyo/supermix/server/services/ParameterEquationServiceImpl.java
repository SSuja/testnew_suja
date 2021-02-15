package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.ParameterEquationEleDto;
import com.tokyo.supermix.data.dto.ParameterEquationElementDto;
import com.tokyo.supermix.data.entities.Equation;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.repositories.EquationRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationElementRepository;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class ParameterEquationServiceImpl implements ParameterEquationService {
  @Autowired
  private ParameterEquationRepository parameterEquationRepository;
  @Autowired
  TestParameterRepository testParameterRepository;
  @Autowired
  ParameterEquationElementRepository parameterEquationElementRepository;
  @Autowired
  EquationRepository equationRepository;

  @Transactional
  public void saveParameterEquation(ParameterEquation parameterEquation) {
    parameterEquationRepository.save(parameterEquation);
  }

  @Transactional(readOnly = true)
  public boolean isEquationIdAndTestParameterId(Long equationId, Long testParameterId,
      Long testConfigureId) {
    Equation equation = equationRepository.findById(equationId).get();
    return parameterEquationRepository
        .existsByEquationFormulaAndTestParameterIdAndTestParameterTestConfigureId(
            equation.getFormula(), testParameterId, testConfigureId);
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
    ParameterEquation parameterEquation = parameterEquationRepository.findById(id).get();
    if (equationRepository.existsById(parameterEquation.getEquation().getId())) {
      parameterEquationRepository.deleteById(id);
      equationRepository.deleteById(parameterEquation.getEquation().getId());
    }
  }

  @Transactional(readOnly = true)
  public ParameterEquation getParameterEquationByTestParameter(Long testParameterId) {
    return parameterEquationRepository.findByTestParameterId(testParameterId);
  }

  @Transactional
  public ParameterEquation updateParameterEquation(ParameterEquation parameterEquation) {
    return parameterEquationRepository.save(parameterEquation);
  }

  @Transactional
  public void saveParameterEquationAndElement(ParameterEquationEleDto parameterEquationEleDto) {
    ArrayList<ParameterEquationElement> parameterEquationElementList =
        new ArrayList<ParameterEquationElement>();
    ParameterEquation parameterEquation = new ParameterEquation();
    parameterEquation
        .setEquation(equationRepository.findById(parameterEquationEleDto.getEquationId()).get());
    parameterEquation.setTestParameter(
        testParameterRepository.findById(parameterEquationEleDto.getTestParameterId()).get());
    saveParameterEquation(parameterEquation);
    for (ParameterEquationElementDto parameterEquationElementDto : parameterEquationEleDto
        .getParameterEquationElements()) {
      ParameterEquationElement parameterEquationElement = new ParameterEquationElement();
      parameterEquationElement.setParameterEquation(parameterEquation);
      parameterEquationElement.setTestParameter(
          testParameterRepository.findById(parameterEquationElementDto.getTestParameterId()).get());
      parameterEquationElementList.add(parameterEquationElement);
      parameterEquationElementRepository.save(parameterEquationElement);
    }
  }

  @Transactional(readOnly = true)
  public List<ParameterEquation> getParameterEquationsByTestConfigureId(Long testConfigureId) {
    return parameterEquationRepository.findByTestParameterTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public boolean isEquationExist(Long equationId) {
    return parameterEquationRepository.existsByEquationId(equationId);
  }

  @Transactional(readOnly = true)
  public boolean isTestParameterExist(Long testParameterId) {
    return parameterEquationRepository.existsByTestParameterId(testParameterId);
  }
}
