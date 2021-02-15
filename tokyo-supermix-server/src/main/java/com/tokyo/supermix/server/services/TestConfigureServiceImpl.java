package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.tokyo.supermix.data.dto.AccepetedValueDto;
import com.tokyo.supermix.data.dto.AcceptedValuesDto;
import com.tokyo.supermix.data.dto.TestConfigureDto;
import com.tokyo.supermix.data.dto.TestConfigureRequestDto;
import com.tokyo.supermix.data.dto.TestConfigureResDto;
import com.tokyo.supermix.data.dto.TestParametersDto;
import com.tokyo.supermix.data.entities.QTestConfigure;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.MainType;
import com.tokyo.supermix.data.enums.ParameterDataType;
import com.tokyo.supermix.data.mapper.Mapper;
import com.tokyo.supermix.data.repositories.AcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.data.repositories.TestParameterRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class TestConfigureServiceImpl implements TestConfigureService {
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private AcceptedValueRepository acceptedValueRepository;
  @Autowired
  private TestParameterRepository testParameterRepository;
  @Autowired
  private Mapper mapper;
  @Autowired
  private EmailPointsService emailPointsService;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private CoreTestConfigureService coreTestConfigureService;

  @Transactional
  public Long saveTestConfigure(TestConfigureRequestDto testConfigureRequestDto) {
    TestConfigure testConfigureobj  = testConfigureRepository.save(mapper.map(testConfigureRequestDto, TestConfigure.class));
    emailPointsService.createEmailPoints(testConfigureobj);
    if (!(testConfigureobj.getDueDay()== null|| testConfigureobj.getDueDay().isEmpty())) {   
      emailPointsService.createScheduleEmailPoints(testConfigureobj);
    }
    coreTestConfigureService.createCoreTestConfigure(testConfigureobj.getId());
    return testConfigureobj.getId();
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
    emailPointsService.deleteByTestConfigureId(id);
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
  public TestConfigureDto getTestConfigureDetailsByConfigureId(Long testConfigId) {
    TestConfigureDto testConfigureDto = new TestConfigureDto();
    TestConfigure testConfigure = testConfigureRepository.findById(testConfigId).get();
    testConfigureDto.setAcceptedType(testConfigure.getAcceptedType());
    testConfigureDto.setPrefix(testConfigure.getPrefix());
    testConfigureDto.setTestName(testConfigure.getTest().getName());
    testConfigureDto.setTestProcedure(testConfigure.getTestProcedure());
    testConfigureDto.setTestType(testConfigure.getTestType());
    if (materialAcceptedValueRepository.findByTestConfigureId(testConfigId) != null) {
      testConfigureDto.setMaterialAcceptedValue(getMaterialAcceptedValue(testConfigId));
    }
    if (acceptedValueRepository.findByTestConfigureId(testConfigId) != null) {
      testConfigureDto.setAcceptedValue(getAcceptedValuesByTestConfigId(testConfigId));
    }
    testConfigureDto.setCoreTest(testConfigure.isCoreTest());
    testConfigureDto.setDescription(testConfigure.getDescription());
    if (testParameterRepository.findByTestConfigureId(testConfigId) != null) {
      testConfigureDto.setTestparameters(getTestParametersByTestConfigId(testConfigId));
    }
    testConfigureDto.setDueDay(testConfigure.getDueDay());
    testConfigureDto.setNoOfTrial(testConfigure.getNoOfTrial());
    return testConfigureDto;
  }

  public List<AccepetedValueDto> getMaterialAcceptedValue(Long testConfigId) {
    ArrayList<AccepetedValueDto> materialAcceptedValueDtoList = new ArrayList<AccepetedValueDto>();
    materialAcceptedValueRepository.findByTestConfigureId(testConfigId)
        .forEach(materialAcceptedValue -> {
          AccepetedValueDto materialAcceptedValueDto = new AccepetedValueDto();
          if (materialAcceptedValue.getRawMaterial() != null) {
            materialAcceptedValueDto
                .setMaterialName(materialAcceptedValue.getRawMaterial().getName());
          } else {
            materialAcceptedValueDto.setMaterialSubCategoryName(
                materialAcceptedValue.getMaterialSubCategory().getName());;
          }

          materialAcceptedValueDto
              .setConditionRange(materialAcceptedValue.getConditionRange().toString());
          materialAcceptedValueDto.setMaxValue(materialAcceptedValue.getMaxValue());
          materialAcceptedValueDto.setMinValue(materialAcceptedValue.getMinValue());
          materialAcceptedValueDto.setValue(materialAcceptedValue.getValue());
          materialAcceptedValueDto.setFinalResult(materialAcceptedValue.isFinalResult());
          if (materialAcceptedValue.getTestParameter() != null) {
            materialAcceptedValueDto.setParameterName(
                materialAcceptedValue.getTestParameter().getParameter().getName());
            materialAcceptedValueDto
                .setTestParameterName(materialAcceptedValue.getTestParameter().getName());
          }
          materialAcceptedValueDtoList.add(materialAcceptedValueDto);

        });
    return materialAcceptedValueDtoList;
  }

  public List<AcceptedValuesDto> getAcceptedValuesByTestConfigId(Long testConfigId) {
    ArrayList<AcceptedValuesDto> acceptedValuesDtoList = new ArrayList<AcceptedValuesDto>();
    acceptedValueRepository.findByTestConfigureId(testConfigId).forEach(acceptedValue -> {
      AcceptedValuesDto acceptedValuesDto = new AcceptedValuesDto();
      acceptedValuesDto.setConditionRange(acceptedValue.getConditionRange());
      acceptedValuesDto.setFinalResult(acceptedValue.isFinalResult());
      acceptedValuesDto.setMaxValue(acceptedValue.getMaxValue());
      acceptedValuesDto.setMinValue(acceptedValue.getMinValue());
      acceptedValuesDto.setTestParameterName(acceptedValue.getTestParameter().getName());
      acceptedValuesDto
          .setTestParameterParameterName(acceptedValue.getTestParameter().getParameter().getName());
      acceptedValuesDto.setValue(acceptedValue.getValue());
      acceptedValuesDtoList.add(acceptedValuesDto);
    });
    return acceptedValuesDtoList;
  }

  public List<TestParametersDto> getTestParametersByTestConfigId(Long testConfigId) {
    ArrayList<TestParametersDto> testParametersDtoList = new ArrayList<TestParametersDto>();
    testParameterRepository.findByTestConfigureId(testConfigId).forEach(testPara -> {
      TestParametersDto testParametersDto = new TestParametersDto();
      testParametersDto.setAbbreviation(testPara.getAbbreviation());
      testParametersDto.setAcceptedCriteria(testPara.isAcceptedCriteria());
      testParametersDto.setName(testPara.getName());
      testParametersDto.setParameterName(testPara.getParameter().getName());
      testParametersDto.setType(testPara.getType());
      testParametersDto.setUnit(testPara.getUnit());
      if (testPara.getParameter().getParameterDataType().equals(ParameterDataType.NUMBER)) {
        if (testPara.getValue() != null) {
          testParametersDto.setValue(testPara.getValue().toString());
        }
      } else {
        if (testPara.getDateValue() != null) {
          testParametersDto.setValue(testPara.getDateValue().toString());
        }
      }
      testParametersDto.setParameterDataType(testPara.getParameter().getParameterDataType());
      testParametersDto.setInputMethods(testPara.getInputMethods());
      testParametersDtoList.add(testParametersDto);
    });
    return testParametersDtoList;
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getTestConfiguresByMaterialSubCategoryAndTestType(
      Long materialSubCategoryId, MainType testType) {
    return testConfigureRepository.findByMaterialSubCategoryIdAndTestType(materialSubCategoryId,
        testType);
  }

  @Transactional
  public Long updateTestConfigure(TestConfigure testConfigure) {
    TestConfigure testConfigureObj= testConfigureRepository.save(testConfigure);
    if(testConfigureObj!=null){
      emailPointsService.updateEmailPoints(testConfigureObj);
      if(!(testConfigureObj.getDueDay() == null)) {
        emailPointsService.updateScheduleEmailPoints(testConfigureObj);
        
      }
      
    }
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

  public TestConfigureResDto getTestConfigureForAcceptedValue(Long testConfigureId) {
    TestConfigureResDto testConfigureResDto = new TestConfigureResDto();
    TestConfigure testConfigure = testConfigureRepository.getOne(testConfigureId);
    testConfigureResDto.setTestConfigureId(testConfigureId);
    testConfigureResDto.setMaterialCategoryId(testConfigure.getMaterialCategory().getId());
    if (testConfigure.getMaterialSubCategory() != null) {
      testConfigureResDto.setMaterialSubCategoryId(testConfigure.getMaterialSubCategory().getId());
      testConfigureResDto
          .setMaterialSubCategoryName(testConfigure.getMaterialSubCategory().getName());
    }
    testConfigureResDto.setMaterialCategoryName(testConfigure.getMaterialCategory().getName());
    testConfigureResDto.setTestName(testConfigure.getTest().getName());
    testConfigureResDto.setAcceptedType(testConfigure.getAcceptedType().toString());
    testConfigureResDto.setTestType(testConfigure.getTestType().toString());
    if (testConfigure.getRawMaterial() != null) {
      testConfigureResDto.setRawMaterialId(testConfigure.getRawMaterial().getId());
      testConfigureResDto.setRawMaterialName(testConfigure.getRawMaterial().getName());
    }
    return testConfigureResDto;
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExists(String prefix) {
    if (testConfigureRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isPrefixAlreadyExistsUpdate(Long id, String prefix) {
    if ((!getTestConfigureById(id).getPrefix().equalsIgnoreCase(prefix))
        && testConfigureRepository.existsByPrefix(prefix)) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> findByMaterialCategory(Long materialCategoryId) {
    return testConfigureRepository
        .findByMaterialCategoryIdAndMaterialSubCategoryNull(materialCategoryId);
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> searchTestConfigure(String testName, MainType mainType,
      String mainCategoryName, String subCategoryName, String materialName,
      BooleanBuilder booleanBuilder, int page, int size, Pageable pageable, Pagination pagination) {
    if (testName != null && !testName.isEmpty()) {
      booleanBuilder.and(QTestConfigure.testConfigure.test.name.contains(testName));
    }
    if (mainType != null) {
      booleanBuilder.and(QTestConfigure.testConfigure.testType.eq(mainType));
    }
    if (mainCategoryName != null && !mainCategoryName.isEmpty()) {
      booleanBuilder
          .and(QTestConfigure.testConfigure.materialCategory.name.contains(mainCategoryName));
    }
    if (subCategoryName != null && !subCategoryName.isEmpty()) {
      booleanBuilder
          .and(QTestConfigure.testConfigure.materialSubCategory.name.contains(subCategoryName));
    }
    if (materialName != null && !materialName.isEmpty()) {
      booleanBuilder.and(QTestConfigure.testConfigure.rawMaterial.name.contains(materialName));
    }
    pagination.setTotalRecords(
        ((Collection<TestConfigure>) testConfigureRepository.findAll(booleanBuilder)).stream()
            .count());
    return testConfigureRepository.findAll(booleanBuilder, pageable).toList();
  }

  @Transactional(readOnly = true)
  public List<TestConfigure> getAllTestConfigureByDecending(Pageable pageable) {
    return testConfigureRepository.findAllByOrderByIdDesc(pageable);
  }

  @Transactional(readOnly = true)
  public Long countTestConfigure() {
    return testConfigureRepository.count();
  }

  @Transactional
  public boolean isTestConfigureByRawMaterialId(Long rawMaterialId) {
    return testConfigureRepository.existsByRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntry(Long id, Long testId, Long materialCategoryId,
      Long materialSubCategoryId, Long rawMaterialId) {
    if (materialSubCategoryId == null) {
      if (!((getTestConfigureById(id).getTest().getId().toString().equals(testId.toString()))
          && getTestConfigureById(id).getMaterialCategory().getId().toString()
              .equals(materialCategoryId.toString()))
          && testConfigureRepository.existsByTestIdAndMaterialCategoryId(testId,
              materialCategoryId)) {
        return true;
      }
    } else if (rawMaterialId != null) {
      if (!((getTestConfigureById(id).getTest().getId().toString().equals(testId.toString())))
          && testConfigureRepository.existsByTestIdAndRawMaterialId(testId, rawMaterialId)) {
        return true;
      }
    } else {
      if (!((getTestConfigureById(id).getTest().getId().toString().equals(testId.toString())))
          && testConfigureRepository.existsByTestIdAndMaterialSubCategoryId(testId,
              materialSubCategoryId)) {
        return true;
      }
    }
    return false;
  }

  @Transactional
  public boolean isMaterialCategory(Long materialCategoryId) {
    return testConfigureRepository.existsByMaterialCategoryId(materialCategoryId);
  }
}
