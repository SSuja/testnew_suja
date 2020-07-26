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
import com.tokyo.supermix.data.dto.AcceptedValueResponseDto;
import com.tokyo.supermix.data.dto.MaterialAcceptedValueResponseDto;
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.dto.TestParameterResponseDto;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.TestType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
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
  @Autowired
  private EmailPointsService emailPointsService;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;

  @Transactional
  public Long saveTestConfigure(TestConfigureRequestDto testConfigureRequestDto) {
    emailPointsService.createEmailPoints(testConfigureRequestDto);
    return testConfigureRepository.save(mapper.map(testConfigureRequestDto, TestConfigure.class))
        .getId();
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
    TestConfigure testconfigure = testConfigureRepository.findById(id).get();
    Long testId = testconfigure.getTest().getId();
    if (testconfigure.getMaterialSubCategory() != null) {
      Long materialSubCategoryId = testconfigure.getMaterialSubCategory().getId();
      if (emailPointsService.findByTestIdAndMaterialSubCategoryId(testId,
          materialSubCategoryId) != null) {
        emailPointsService.deleteByTestIdAndMaterialSubCategoryId(testId, materialSubCategoryId);
      }
    } else {
      Long materialCategoryId = testconfigure.getMaterialCategory().getId();
      if (emailPointsService.findByTestIdAndMaterialCategoryId(testId,
          materialCategoryId) != null) {
        emailPointsService.deleteByTestIdAndMaterialCategoryId(testId, materialCategoryId);
      }
    }
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
  public boolean isexistByTestIdAndMaterialCategoryIdAndMaterialSubCategoryId(Long testId,
      Long materialCategoryId, Long materialSubCategoryId) {
    if (testConfigureRepository.existsByTestIdAndMaterialCategoryIdAndMaterialSubCategoryId(testId,
        materialCategoryId, materialSubCategoryId)) {
      return true;
    }
    return false;
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
    if (testConfigure.getMaterialSubCategory() != null) {
      testConfigureDto.setMaterialSubCategory(mapper.map(
          materialSubCategoryRepository.findById(testConfigure.getMaterialSubCategory().getId()).get(), MaterialSubCategoryResponseDto.class));
    }
    testConfigureDto.setPrefix(testConfigure.getPrefix());
    testConfigureDto.setTestName(testConfigure.getTest().getName());
    testConfigureDto.setTestProcedure(testConfigure.getTestProcedure());
    testConfigureDto.setTestType(testConfigure.getTestType());
    if (materialAcceptedValueRepository.findByTestConfigureId(id) != null) {
      testConfigureDto.setMaterialAcceptedValue(
          mapper.map(materialAcceptedValueRepository.findByTestConfigureId(id),
              MaterialAcceptedValueResponseDto.class));
    }
    if (acceptedValueRepository.findByTestConfigureId(id) != null) {
      testConfigureDto.setAcceptedValue(mapper
          .map(acceptedValueRepository.findByTestConfigureId(id), AcceptedValueResponseDto.class));
    }
    testConfigureDto.setCoreTest(testConfigure.isCoreTest());
    testConfigureDto.setDescription(testConfigure.getDescription());
    if (testParameterRepository.findByTestConfigureId(id) != null) {
      testConfigureDto.setTestparameters(mapper
          .map(testParameterRepository.findByTestConfigureId(id), TestParameterResponseDto.class));
    }
    return testConfigureDto;
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getTestConfiguresByMaterialSubCategoryAndTestType(
      Long materialSubCategoryId, TestType testType) {
    return testConfigureRepository.findByMaterialSubCategoryIdAndTestType(materialSubCategoryId,
        testType);
  }

  @Transactional
  public Long updateTestConfigure(TestConfigure testConfigure) {
    testConfigureRepository.save(testConfigure);
    return testConfigure.getId();
  }
}
