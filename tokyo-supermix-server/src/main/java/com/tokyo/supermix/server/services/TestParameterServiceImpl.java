package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.ParameterEquationDto;
import com.tokyo.supermix.data.dto.TestConfigureResponseDto;
import com.tokyo.supermix.data.dto.TestParameterEquationDto;
import com.tokyo.supermix.data.dto.TestParameterResponseDto;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class TestParameterServiceImpl implements TestParameterService {
  @Autowired
  private TestParameterRepository testParameterRepository;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private ParameterEquationRepository parameterEquationRepository;
  @Autowired
  private Mapper mapper;

  @Transactional
  public void saveTestParameter(TestParameter testParameter) {
    testParameterRepository.save(testParameter);
  }

  @Transactional(readOnly = true)
  public List<TestParameter> getAllTestParameters() {
    return testParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isTestParameterExist(Long id) {
    return testParameterRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public TestParameter getTestParameterById(Long id) {
    return testParameterRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestParameter(Long id) {
    testParameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureIdExist(Long id) {
    return testParameterRepository.existsByTestConfigureId(id);
  }

  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, String abbreviation) {
    return testParameterRepository.existsByTestConfigureIdAndAbbreviation(testConfigureId,
        abbreviation);
  }

  @Transactional(readOnly = true)
  public Page<TestParameter> searchTestParameter(Predicate predicate, int size, int page) {
    return testParameterRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public List<TestParameter> getAllTestParametersByTestConfigureId(Long testConfigureId) {
    List<TestParameter> testParameterLists =
        testParameterRepository.findByTestConfigureId(testConfigureId);
    List<TestParameter> testParameters = new ArrayList<TestParameter>();
    for (TestParameter testParameter : testParameterLists) {
      if (testParameter.getParameter() != null) {
        testParameters.add(testParameter);
      }
    }
    return testParameters;
  }

  @Transactional(readOnly = true)
  public boolean isAbbreviationNull(String abbreviation) {
    if (abbreviation.isEmpty()) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<TestParameter> getAllParametersByDecending() {
    return testParameterRepository.findAllByOrderByIdDesc();
  }

  @Transactional(readOnly = true)
  public boolean isParameterIdExist(Long parameterId) {
    if (testParameterRepository.existsByParameterId(parameterId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isAbbreviationExists(String abbreviation) {
    if (testParameterRepository.existsByAbbreviation(abbreviation)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public TestParameterEquationDto getTestParameterEquation(Long testConfigureId) {
    TestParameterEquationDto testParameterEquationDto = new TestParameterEquationDto();
    List<TestParameter> testParameterList =
        testParameterRepository.findByTestConfigureId(testConfigureId);
    List<TestParameterResponseDto> testParameterResponseDtoList =
        new ArrayList<TestParameterResponseDto>();
    testParameterList.forEach(test -> {
      TestParameterResponseDto testParameterResponseDto = new TestParameterResponseDto();
      testParameterResponseDto.setId(test.getId());
      testParameterResponseDto.setAbbreviation(test.getAbbreviation());
      testParameterResponseDto.setType(test.getType());
      testParameterResponseDto.setParameter(test.getParameter());
      testParameterResponseDto.setTestConfigure(getTestConfigureDetails(testConfigureId));
      testParameterResponseDto.setUnit(test.getUnit());
      testParameterResponseDto.setValue(test.getValue());
      testParameterResponseDtoList.add(testParameterResponseDto);
    });
    List<ParameterEquation> parameterEquationList =
        parameterEquationRepository.findByTestParameterTestConfigureId(testConfigureId);
    List<ParameterEquationDto> parameterEquationResponseDtoList =
        new ArrayList<ParameterEquationDto>();
    parameterEquationList.forEach(parameter -> {
      ParameterEquationDto parameterEquationDto = new ParameterEquationDto();
      parameterEquationDto.setTestParameterId(parameter.getTestParameter().getId());
      parameterEquationDto.setParameterEquation(parameter.getEquation().getFormula());
      parameterEquationDto.setAbbreviation(parameter.getTestParameter().getAbbreviation());
      parameterEquationResponseDtoList.add(parameterEquationDto);
    });
    testParameterEquationDto.setTestParameters(testParameterResponseDtoList);
    testParameterEquationDto.setParameterEquations(parameterEquationResponseDtoList);
    return testParameterEquationDto;
  }

  private TestConfigureResponseDto getTestConfigureDetails(Long testConfigureId) {
    TestConfigure testConfigure = testConfigureRepository.findById(testConfigureId).get();
    TestConfigureResponseDto testConfigureResponseDto =
        mapper.map(testConfigure, TestConfigureResponseDto.class);
    return testConfigureResponseDto;
  }
}
