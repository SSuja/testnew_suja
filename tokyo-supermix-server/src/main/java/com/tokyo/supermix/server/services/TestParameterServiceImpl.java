package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
import com.tokyo.supermix.data.dto.TestParameterRequestDto;
import com.tokyo.supermix.data.dto.TestParameterResponseDto;
import com.tokyo.supermix.data.dto.report.Level;
import com.tokyo.supermix.data.entities.ParameterEquation;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.InputMethod;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.ParameterEquationRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.util.Constants;

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
  public void saveTestParameterAll(List<TestParameter> testParameter) {
    testParameterRepository.saveAll(testParameter);
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

  @Transactional(readOnly = true)
  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, String abbreviation,
      Long parameterId) {
    if (testParameterRepository.existsByTestConfigureIdAndAbbreviationAndParameterId(
        testConfigureId, abbreviation, parameterId)) {
      return true;
    }
    return false;
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
      if (test.getQualityParameter() == null) {
        TestParameterResponseDto testParameterResponseDto = new TestParameterResponseDto();
        testParameterResponseDto.setId(test.getId());
        testParameterResponseDto.setAbbreviation(test.getAbbreviation());
        testParameterResponseDto.setType(test.getType());
        testParameterResponseDto.setParameter(test.getParameter());
        testParameterResponseDto.setTestConfigure(getTestConfigureDetails(testConfigureId));
        testParameterResponseDto.setInputMethods(test.getInputMethods());
        testParameterResponseDto.setUnit(test.getUnit());
        if(test.getParameter().getParameterDataType().equals(ParameterDataType.DATETIME)) {
          testParameterResponseDto.setDateValue(test.getDateValue());
        }else {
        testParameterResponseDto.setValue(test.getValue());
        }
        testParameterResponseDto.setAcceptedCriteria(test.isAcceptedCriteria());
        testParameterResponseDto.setName(test.getName());
        if (test.getTableFormat() != null) {
          testParameterResponseDto.setTableFormatId(test.getTableFormat().getId());
          testParameterResponseDto.setTableFormatName(test.getTableFormat().getTableName());
        }
        testParameterResponseDtoList.add(testParameterResponseDto);
      }
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

  public List<Level> getLevelsByTestConfigureId(Long testConfigId) {
    ArrayList<Level> levelList = new ArrayList<Level>();
    for (TestParameter testParameter : testParameterRepository
        .findByTestConfigureId(testConfigId)) {
      Level levels = new Level();
      if (testParameter.getName() != null) {
        String[] parts = testParameter.getName().split("_");
        levels.setLevel(parts[1]);
        testParameter.setLevel(parts[1]);
        testParameterRepository.save(testParameter);
      } else {
        testParameter.setLevel("result");
        levels.setLevel("result");
        testParameterRepository.save(testParameter);
      }
      levelList.add(levels);
    }
    return levelList;
  }

  public Set<String> getAllOriLevel(Long testConfigId) {
    List<String> originalLevelList = new ArrayList<String>();
    for (Level levels : getLevelsByTestConfigureId(testConfigId)) {
      originalLevelList.add(levels.getLevel());
    }
    originalLevelList.toString();
    Set<String> oriList = new LinkedHashSet<String>(originalLevelList);
    return oriList;
  }

  @Transactional(readOnly = true)
  public boolean isParameterExists(Long testConfigureId, Long parameterId) {
    return testParameterRepository.existsByTestConfigureIdAndParameterId(testConfigureId,
        parameterId);
  }

  public boolean isUpdatedAbbreviationExists(Long id, Long testConfigureId, String abbreviation) {
    if ((!getTestParameterById(id).getAbbreviation().equalsIgnoreCase(abbreviation))
        && (isTestConfigureAndAbbreviationExist(testConfigureId, abbreviation))) {
      return true;
    }
    return false;
  }

  public String checkEqutaionExistsForTest(Long testConfigureId) {
    String isEquationExists = Constants.CHECK_EQUATION_FALSE;
    List<TestParameter> testParam = testParameterRepository.findByTestConfigureId(testConfigureId);
    for (TestParameter testParameter : testParam) {
      if ((testParameter.getInputMethods().equals(InputMethod.CALCULATION))) {
        isEquationExists = Constants.CHECK_EQUATION_TRUE;
        break;
      }
    }
    return isEquationExists;
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureAndAbbreviationExist(Long testConfigureId, String abbreviation) {
    if (testParameterRepository.existsByTestConfigureIdAndAbbreviation(testConfigureId,
        abbreviation)) {
      return true;
    }
    return false;
  }

  public boolean isUpdatedParameterExists(Long id, Long testConfigureId, Long parameterId) {
    if ((!getTestParameterById(id).getParameter().getId().equals(parameterId))
        && (isParameterExists(testConfigureId, parameterId))) {
      return true;
    }
    return false;
  }

  @Transactional
  public void saveTestParameter(TestParameter testParameter) {
    testParameterRepository.save(testParameter);
  }

  @Transactional(readOnly = true)
  public boolean checkAbbreviation(List<TestParameterRequestDto> testParameterRequestDtos) {
    ArrayList<String> testParameterList = new ArrayList<String>();
    for (TestParameterRequestDto testParameterRequestDto : testParameterRequestDtos) {
      testParameterList.add(testParameterRequestDto.getAbbreviation());
    }
    if (testParameterList.size() != testParameterList.stream().distinct()
        .collect(Collectors.toList()).size()) {
      return true;
    }
    return false;
  }
}
