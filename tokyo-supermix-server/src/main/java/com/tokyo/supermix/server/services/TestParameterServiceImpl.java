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
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.EntryLevel;
import com.tokyo.supermix.data.repositories.IncomingSampleRepository;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class TestParameterServiceImpl implements TestParameterService {
  @Autowired
  private TestParameterRepository testParameterRepository;
  @Autowired
  private IncomingSampleRepository incomingSampleRepository;
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;

  @Transactional
  public List<TestParameter> saveTestParameter(List<TestParameter> testParameter) {
    return testParameterRepository.saveAll(testParameter);
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
  public List<TestParameter> getTestParameterByTestConfigureId(Long testConfigureId,
      String incomingSampleCode) {
    Long rawMaterialId =
        incomingSampleRepository.findById(incomingSampleCode).get().getRawMaterial().getId();
    List<TestParameter> testParameterLists =
        testParameterRepository.findByTestConfigureId(testConfigureId);
    List<TestParameter> testParameters = new ArrayList<TestParameter>();
    for (TestParameter testParameter : testParameterLists) {
      if (testParameter.getQualityParameter() == null) {
        System.out.println("quality parameter" + testParameter.getQualityParameter());
        System.out.println("rawMaterialId" + rawMaterialId);
        if (testParameter.getParameter() != null && testParameter.getValue() == null
            && testParameter.getEntryLevel().equals(EntryLevel.TEST)) {
          testParameters.add(testParameter);
        }
      }
      if (testParameter.getParameter() == null) {
        if (materialQualityParameterRepository.findByQualityParameterIdAndRawMaterialId(
            testParameter.getQualityParameter().getId(), rawMaterialId) != null) {
          if ((testParameter.getQualityParameter() != null && testParameter.getValue() == null
              && materialQualityParameterRepository.findByQualityParameterIdAndRawMaterialId(
                  testParameter.getQualityParameter().getId(), rawMaterialId).getValue() == null
              && testParameter.getEntryLevel().equals(EntryLevel.TEST))) {
            testParameters.add(testParameter);
          }
        } else if ((materialQualityParameterRepository.findByQualityParameterIdAndRawMaterialId(
            testParameter.getQualityParameter().getId(), rawMaterialId) == null
            && testParameter.getQualityParameter() != null && testParameter.getValue() == null)) {
          testParameters.add(testParameter);
        }
      }
    }
    return testParameters;
  }


  @Transactional(readOnly = true)
  public boolean isTestConfigureIdExist(Long id) {
    return testParameterRepository.existsByTestConfigureId(id);
  }

  @Transactional
  public TestParameter updateTestParameter(TestParameter testParameter) {
    return testParameterRepository.save(testParameter);
  }

  public boolean isDuplicateTestParameterEntryExist(Long testConfigureId, Long parameterId,
      Long unitId, String abbreviation, EntryLevel entryLevel) {
    if (testParameterRepository
        .existsByTestConfigureIdAndParameterIdAndUnitIdAndAbbreviationAndEntryLevel(testConfigureId,
            parameterId, unitId, abbreviation, entryLevel)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public Page<TestParameter> searchTestParameter(Predicate predicate, int size, int page) {
    return testParameterRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  public boolean isDuplicateQualityTestParameterEntryExist(Long testConfigureId,
      Long qualityParameterId, Long unitId, String abbreviation, EntryLevel entryLevel) {
    if (testParameterRepository
        .existsByTestConfigureIdAndQualityParameterIdAndUnitIdAndAbbreviationAndEntryLevel(
            testConfigureId, qualityParameterId, unitId, abbreviation, entryLevel)) {
      return true;
    }
    return false;
  }
}
