package com.tokyo.supermix.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.dto.TestParameterDto;
import com.tokyo.supermix.data.dto.report.AcceptedValueDto;
import com.tokyo.supermix.data.entities.AcceptedValue;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.entities.TestParameter;
import com.tokyo.supermix.data.enums.TestType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;

@Service
public class TestConfigureServiceImpl implements TestConfigureService {
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private TestParameterRepository testParameterRepository;
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;
  @Autowired
  private Mapper mapper;

  @Transactional
  public Long saveTestConfigure(TestConfigure testConfigure) {
    testConfigureRepository.save(testConfigure);
    return testConfigure.getId();
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureExist(Long id) {
    return testConfigureRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getAllTestConfigures() {
    return testConfigureRepository.findAll();
  }

  @Transactional(readOnly = true)
  public TestConfigure getTestConfigureById(Long id) {
    return testConfigureRepository.findById(id).get();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteTestConfigure(Long id) {
    testConfigureRepository.deleteById(id);
  }

  @Transactional
  public void updateCoreTestForTestConfigure(Long id, boolean coreTest) {
    TestConfigure testConfigure = testConfigureRepository.findById(id).get();
    testConfigure.setCoreTest(coreTest);
    testConfigureRepository.save(testConfigure);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByCoreTest(boolean coreTest) {
    return testConfigureRepository.findByCoreTest(coreTest);
  }

  @Transactional(readOnly = true)
  public Page<TestConfigure> searchTestConfigure(Predicate predicate, int page, int size) {
    return testConfigureRepository.findAll(predicate,
        PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
  }

  @Transactional(readOnly = true)
  public TestConfigureDto getTestDetailsByConfigureId(Long id) {
    TestConfigureDto testConfigureDto = new TestConfigureDto();
    TestConfigure testConfigure = testConfigureRepository.findById(id).get();
    AcceptedValue acceptedValue = acceptedValueRepository.findByTestConfigureId(id);
    List<TestParameter> testParameter = testParameterRepository.findByTestConfigureId(id);
    MaterialSubCategory materialSubCategory = materialSubCategoryRepository.findById(id).get();
    testConfigureDto.setMaterialSubCategory(
        mapper.map(materialSubCategory, MaterialSubCategoryResponseDto.class));
    testConfigureDto.setId(testConfigure.getId());
    testConfigureDto.setPrefix(testConfigure.getPrefix());
    testConfigureDto.setTestName(testConfigure.getTest().getName());
    testConfigureDto.setTestProcedure(testConfigure.getTestProcedure());
    testConfigureDto.setTestType(testConfigure.getTestType());
    testConfigureDto.setAcceptedValue(mapper.map(acceptedValue, AcceptedValueDto.class));
    testConfigureDto.setCoreTest(testConfigure.isCoreTest());
    testConfigureDto.setFormula(testConfigure.getEquation().getFormula());
    testConfigureDto.setDescription(testConfigure.getDescription());
    List<TestParameterDto> testParameterList = mapper.map(testParameter, TestParameterDto.class);
    testConfigureDto.setTestparameters(testParameterList);
    return testConfigureDto;
  }

  @Transactional(readOnly = true)
  public boolean isexistByTestIdAndMaterialCategoryIdAndMaterialSubCategoryId(Long testId,
      Long materialCategoryId, Long materialSubCategoryId) {
    return testConfigureRepository.existsByTestIdAndMaterialCategoryIdAndMaterialSubCategoryId(
        testId, materialCategoryId, materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByTestType(TestType testType) {
    return testConfigureRepository.findByTestType(testType);
  }

  @Transactional(readOnly = true)
  public boolean isexistByTestType(TestType testType) {
    return testConfigureRepository.existsByTestType(testType);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByMaterialSubCategory(Long materialSubCategoryId) {
    return testConfigureRepository.findByMaterialSubCategoryId(materialSubCategoryId);
  }

  @Transactional(readOnly = true)
  public TestConfigureDto getTestConfigureDetailsByConfigureId(Long id) {
    TestConfigureDto testConfigureDto = new TestConfigureDto();
    TestConfigure testConfigure = testConfigureRepository.findById(id).get();
    AcceptedValue acceptedValue = acceptedValueRepository.findByTestConfigureId(id);
    List<TestParameter> testParameter = testParameterRepository.findByTestConfigureId(id);
    MaterialSubCategory materialSubCategory = materialSubCategoryRepository.findById(id).get();
    testConfigureDto.setMaterialSubCategory(
        mapper.map(materialSubCategory, MaterialSubCategoryResponseDto.class));
    testConfigureDto.setId(testConfigure.getId());
    testConfigureDto.setPrefix(testConfigure.getPrefix());
    testConfigureDto.setTestName(testConfigure.getTest().getName());
    testConfigureDto.setTestProcedure(testConfigure.getTestProcedure());
    testConfigureDto.setTestType(testConfigure.getTestType());
    testConfigureDto.setAcceptedValue(mapper.map(acceptedValue, AcceptedValueDto.class));
    testConfigureDto.setCoreTest(testConfigure.isCoreTest());
    testConfigureDto.setDescription(testConfigure.getDescription());
    List<TestParameterDto> testParameterList = mapper.map(testParameter, TestParameterDto.class);
    testConfigureDto.setTestparameters(testParameterList);
    return testConfigureDto;
  }
}
