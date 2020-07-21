package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.TestEquationDto;
import com.tokyo.supermix.data.dto.TestEquationParameterDto;
import com.tokyo.supermix.data.entities.TestEquation;
import com.tokyo.supermix.data.entities.TestEquationParameter;
import com.tokyo.supermix.data.repositories.EquationRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestEquationParameterRepository;
import com.tokyo.supermix.data.repositories.TestEquationRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class TestEquationServiceImpl implements TestEquationService {
  @Autowired
  private TestEquationRepository testEquationRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private TestParameterRepository testParameterRepository;
  @Autowired
  private TestEquationParameterRepository testEquationParameterRepository;
  @Autowired
  private EquationRepository equationRepository;

  @Transactional
  public TestEquation saveTestEquation(TestEquation testEquation) {
    return testEquationRepository.save(testEquation);
  }

  @Transactional(readOnly = true)
  public List<TestEquation> getAllTestEquations() {
    return testEquationRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntry(Long testConfigureId, Long equationId) {
    return testEquationRepository.existsByTestConfigureIdAndEquationId(testConfigureId, equationId);
  }

  @Transactional(readOnly = true)
  public TestEquation getByTestEquationId(Long id) {
    return testEquationRepository.findById(id).get();
  }

  @Transactional(readOnly = true)
  public boolean isTestEquationExists(Long id) {
    return testEquationRepository.existsById(id);
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestEquation(Long id) {
    testEquationRepository.deleteById(id);
  }

  @Transactional
  public void saveTestEquationAndTestEquationParameter(TestEquationDto testEquationDto) {
    TestEquation testEquation = new TestEquation();
    ArrayList<TestEquationParameter> testEquationParameterList =
        new ArrayList<TestEquationParameter>();
    testEquation.setTestConfigure(
        testConfigureRepository.findById(testEquationDto.getTestConfigId()).get());
    testEquation.setEquation(equationRepository.findById(testEquationDto.getEquationId()).get());
    testEquationRepository.save(testEquation);
    for (TestEquationParameterDto testEquationParameterDto : testEquationDto
        .getTestEquationParameters()) {
      TestEquationParameter testEquationParameter = new TestEquationParameter();
      testEquationParameter.setTestEquation(testEquation);
      testEquationParameter.setTestParameter(
          testParameterRepository.findById(testEquationParameterDto.getTestParameterId()).get());
      testEquationParameterList.add(testEquationParameter);
      testEquationParameterRepository.save(testEquationParameter);
    }
  }
}
