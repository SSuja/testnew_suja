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
import com.tokyo.supermix.data.dto.AcceptedValueResponseDto;
import com.tokyo.supermix.data.dto.FinishProductAcceptedValueResponseDto;
import com.tokyo.supermix.data.dto.MaterialSubCategoryResponseDto;
import com.tokyo.supermix.data.dto.RawMaterialResponseDto;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.dto.TestParameterResponseDto;
import com.tokyo.supermix.data.dto.report.MaterialAcceptedValueDto;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.FinishProductAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
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
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmailPointsService emailPointsService;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private FinishProductAcceptedValueRepository finishProductAcceptedValueRepository;

  @Transactional
  public Long saveTestConfigure(TestConfigureRequestDto testConfigureRequestDto) {
    if (testConfigureRequestDto.getMaterialSubCategoryId() != null && (emailPointsService
        .findByTestIdAndMaterialSubCategoryId(testConfigureRequestDto.getTestId(),
            testConfigureRequestDto.getMaterialSubCategoryId())) == null) {
      emailPointsService.createEmailPoints(testConfigureRequestDto);
    } else if (testConfigureRequestDto.getMaterialSubCategoryId() == null
        && emailPointsService.findByTestIdAndMaterialCategoryId(testConfigureRequestDto.getTestId(),
            testConfigureRequestDto.getMaterialCategoryId()) == null) {
      emailPointsService.createEmailPoints(testConfigureRequestDto);
    }
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
  public boolean isExistByTestIdAndMaterialSubCategoryId(Long testId, Long materialSubCategoryId,
      Long rawMaterialId) {
    if (testConfigureRepository.existsByTestIdAndMaterialSubCategoryIdAndRawMaterialId(testId,
        materialSubCategoryId, rawMaterialId)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByTestType(MainType testType) {
    return testConfigureRepository.findByTestType(testType);
  }

  @Transactional(readOnly = true)
  public boolean isexistByTestType(MainType testType) {
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
      testConfigureDto
          .setMaterialSubCategory(
              mapper.map(
                  materialSubCategoryRepository
                      .findById(testConfigure.getMaterialSubCategory().getId()).get(),
                  MaterialSubCategoryResponseDto.class));
    }
    if (testConfigure.getRawMaterial() != null) {
      testConfigureDto.setRawMaterial(
          mapper.map(rawMaterialRepository.findById(testConfigure.getRawMaterial().getId()).get(),
              RawMaterialResponseDto.class));
    }
    testConfigureDto.setAcceptedType(testConfigure.getAcceptedType());
    testConfigureDto.setPrefix(testConfigure.getPrefix());
    testConfigureDto.setTestName(testConfigure.getTest().getName());
    testConfigureDto.setTestProcedure(testConfigure.getTestProcedure());
    testConfigureDto.setTestType(testConfigure.getTestType());
    if (materialAcceptedValueRepository.findByTestConfigureId(id) != null) {
      testConfigureDto.setMaterialAcceptedValue(getMaterialAcceptedValue(id));
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
    if (finishProductAcceptedValueRepository.existsByTestParameterTestConfigureId(id)) {
      testConfigureDto.setFinishProductAcceptedValue(
          mapper.map(finishProductAcceptedValueRepository.findByTestParameterTestConfigureId(id),
              FinishProductAcceptedValueResponseDto.class));
    }
    return testConfigureDto;
  }

  public List<MaterialAcceptedValueDto> getMaterialAcceptedValue(Long testConfigId) {
    ArrayList<MaterialAcceptedValueDto> materialAcceptedValueDtoList =
        new ArrayList<MaterialAcceptedValueDto>();
    List<MaterialAcceptedValue> materialAcceptedValueList =
        materialAcceptedValueRepository.findByTestConfigureId(testConfigId);
    for (MaterialAcceptedValue materialAcceptedValue : materialAcceptedValueList) {
      MaterialAcceptedValueDto materialAcceptedValueDto = new MaterialAcceptedValueDto();
      materialAcceptedValueDto.setMaterialName(materialAcceptedValue.getRawMaterial().getName());
      materialAcceptedValueDto.setMaxValue(materialAcceptedValue.getMaxValue());
      materialAcceptedValueDto.setMinValue(materialAcceptedValue.getMinValue());
      materialAcceptedValueDto.setValue(materialAcceptedValue.getValue());
      materialAcceptedValueDto
          .setTestName(materialAcceptedValue.getTestConfigure().getTest().getName());
      materialAcceptedValueDto.setFinalResult(materialAcceptedValue.isFinalResult());
      materialAcceptedValueDto
          .setParameter(materialAcceptedValue.getTestParameter().getParameter().getName());
      materialAcceptedValueDto.setName(materialAcceptedValue.getTestParameter().getName());
      materialAcceptedValueDtoList.add(materialAcceptedValueDto);

    }
    return materialAcceptedValueDtoList;
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getTestConfiguresByMaterialSubCategoryAndTestType(
      Long materialSubCategoryId, MainType testType) {
    return testConfigureRepository.findByMaterialSubCategoryIdAndTestType(materialSubCategoryId,
        testType);
  }

  @Transactional
  public Long updateTestConfigure(TestConfigure testConfigure) {
    testConfigureRepository.save(testConfigure);
    return testConfigure.getId();
  }

  public boolean isUpdatedMaterialSubCategoryAndTest(Long id, Long testId,
      Long materialSubCategoryId, Long rawMaterialId) {
    if ((!getTestConfigureById(id).getTest().getId().equals(testId))
        && (!getTestConfigureById(id).getMaterialSubCategory().getId()
            .equals(materialSubCategoryId))
        && (!getTestConfigureById(id).getRawMaterial().getId().equals(rawMaterialId))
        && (isExistByTestIdAndMaterialSubCategoryId(testId, materialSubCategoryId,
            rawMaterialId))) {
      return true;
    }
    return false;
  }
}
