package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.ParameterEquationElement;
import com.tokyo.supermix.data.entities.TestParameter;
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

  @Transactional
  public void saveParameterEquation(ParameterEquation parameterEquation) {
    parameterEquationRepository.save(parameterEquation);
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


  public void updateParameterEquationElements(ParameterEquation parameterEquation) {
    ArrayList<ParameterEquationElement> parameterEquationElementList =
        new ArrayList<ParameterEquationElement>();
    parameterEquation = parameterEquationRepository.findById(parameterEquation.getId()).get();
    List<TestParameter> testParameterList = testParameterRepository
        .findByTestConfigureId(parameterEquation.getTestParameter().getTestConfigure().getId());
    for (TestParameter testParameter : testParameterList) {
      ParameterEquationElement parameterEquationElement = new ParameterEquationElement();
      parameterEquationElement.setParameterEquation(parameterEquation);
      parameterEquationElement.setTestParameter(testParameter);
      parameterEquationElementList.add(parameterEquationElement);
      parameterEquationElementRepository.save(parameterEquationElement);
    }
  }
}
