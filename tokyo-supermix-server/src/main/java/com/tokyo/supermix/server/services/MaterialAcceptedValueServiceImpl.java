package com.tokyo.supermix.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.core.BooleanBuilder;
import com.tokyo.supermix.data.dto.AccepetedValueDto;
import com.tokyo.supermix.data.dto.AcceptedValueMainDto;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialSubCategory;
import com.tokyo.supermix.data.entities.QMaterialAcceptedValue;
import com.tokyo.supermix.data.entities.RawMaterial;
import com.tokyo.supermix.data.entities.TestConfigure;
import com.tokyo.supermix.data.enums.CategoryAcceptedType;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.TestResultType;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialSubCategoryRepository;
import com.tokyo.supermix.data.repositories.RawMaterialRepository;
import com.tokyo.supermix.data.repositories.TestConfigureRepository;
import com.tokyo.supermix.rest.response.PaginatedContentResponse.Pagination;

@Service
public class MaterialAcceptedValueServiceImpl implements MaterialAcceptedValueService {

  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;
  @Autowired
  private TestConfigureService testConfigureService;
  @Autowired
  private TestConfigureRepository testConfigureRepository;
  @Autowired
  private RawMaterialRepository rawMaterialRepository;
  @Autowired
  private MaterialSubCategoryRepository materialSubCategoryRepository;

  @Transactional
  public List<MaterialAcceptedValue> saveAcceptedValue(
      List<MaterialAcceptedValue> materialAcceptedValue) {
    return materialAcceptedValueRepository.saveAll(materialAcceptedValue);
  }

  @Transactional
  public void updateMaterialAcceptedValue(MaterialAcceptedValue materialAcceptedValue) {
    materialAcceptedValueRepository.save(materialAcceptedValue);
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getAllMaterialAcceptedValues() {
    return materialAcceptedValueRepository.findAll();
  }

  @Transactional(readOnly = true)
  public boolean isMaterialAcceptedValueExist(Long id) {
    return materialAcceptedValueRepository.existsById(id);
  }

  @Transactional(readOnly = true)
  public MaterialAcceptedValue getMaterialAcceptedValueById(Long id) {
    return materialAcceptedValueRepository.findById(id).get();
  }

  @Transactional
  public void deleteMaterialAcceptedValue(Long id) {
    MaterialAcceptedValue materialAcceptedValue =
        materialAcceptedValueRepository.findById(id).get();
    materialAcceptedValueRepository.deleteById(id);
    upDateTesConfigureType(materialAcceptedValue.getTestConfigure().getId());
  }

  @Transactional(readOnly = true)
  public List<MaterialAcceptedValue> getMaterialAcceptedValueByTestConfigure(
      TestConfigure testConfigure) {
    return materialAcceptedValueRepository.findByTestConfigure(testConfigure);
  }

  @Transactional(readOnly = true)
  public boolean isDuplicateEntryExist(Long testConfigureId, Long rawMaterialId) {
    return materialAcceptedValueRepository.existsByTestConfigureIdAndRawMaterialId(testConfigureId,
        rawMaterialId);
  }

  public boolean isUpdatedRawMaterialIdExist(Long id, Long testConfigureId, Long rawMaterialId) {
    if ((!getMaterialAcceptedValueById(id).getRawMaterial().getId().equals(rawMaterialId))
        && (isDuplicateEntryExist(testConfigureId, rawMaterialId))) {
      return true;
    }
    return false;
  }

  @Transactional(readOnly = true)
  public boolean isRawMaterialIdExist(Long rawMaterialId) {
    return materialAcceptedValueRepository.existsByRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureIdAndRawMaterialIdAndTestParameterId(Long testConfigureId,
      Long rawMaterialId, Long testParameterId) {
    return materialAcceptedValueRepository
        .existsByTestConfigureIdAndRawMaterialIdAndTestParameterId(testConfigureId, rawMaterialId,
            testParameterId);
  }

  @Transactional(readOnly = true)
  public boolean isCheckValidation(List<MaterialAcceptedValue> materialAcceptedValueList) {
    for (MaterialAcceptedValue materialAcceptedValue : materialAcceptedValueList) {
      if (materialAcceptedValue.getConditionRange().equals(Condition.BETWEEN)) {
        if (materialAcceptedValue.getMaxValue() == null
            || materialAcceptedValue.getMinValue() == null) {
          return true;
        }
      } else if (materialAcceptedValue.getConditionRange().equals(Condition.GREATER_THAN)
          || materialAcceptedValue.getConditionRange().equals(Condition.LESS_THAN)
          || materialAcceptedValue.getConditionRange().equals(Condition.EQUAL)) {
        if (materialAcceptedValue.getValue() == null) {
          return true;
        }
      }
    }
    return false;
  }

  @Transactional(readOnly = true)
  public AcceptedValueMainDto findByTestConfigureId(Long testConfigureId) {
    AcceptedValueMainDto acceptedValueMainDto = new AcceptedValueMainDto();
    acceptedValueMainDto.setTestConfigureResDto(
        testConfigureService.getTestConfigureForAcceptedValue(testConfigureId));
    acceptedValueMainDto.setAccepetedValueDto(getMaterialAccepetedValueDtoList(testConfigureId));
    return acceptedValueMainDto;
  }

  private List<AccepetedValueDto> getMaterialAccepetedValueDtoList(Long testConfigureId) {
    List<AccepetedValueDto> accepetedValueDtolist = new ArrayList<>();
    materialAcceptedValueRepository.findByTestConfigureId(testConfigureId).stream()
        .forEach(acceptedValue -> {
          AccepetedValueDto accepetedValueDto = new AccepetedValueDto();
          accepetedValueDto.setId(acceptedValue.getId());
          accepetedValueDto.setConditionRange(acceptedValue.getConditionRange().toString());
          accepetedValueDto.setMinValue(acceptedValue.getMinValue());
          accepetedValueDto.setMaxValue(acceptedValue.getMaxValue());
          accepetedValueDto.setFinalResult(acceptedValue.isFinalResult());
          accepetedValueDto.setValue(acceptedValue.getValue());
          accepetedValueDto.setCategoryAcceptedType(acceptedValue.getCategoryAcceptedType());
          if (acceptedValue.getTestEquation() != null) {
            accepetedValueDto.setTestEquationId(acceptedValue.getTestEquation().getId());
            accepetedValueDto
                .setTestEquationFormula(acceptedValue.getTestEquation().getEquation().getFormula());
          }
          if (acceptedValue.getRawMaterial() != null) {
            accepetedValueDto.setMaterialId(acceptedValue.getRawMaterial().getId());
            accepetedValueDto.setMaterialName(acceptedValue.getRawMaterial().getName());
          }
          accepetedValueDto
              .setParameterName(acceptedValue.getTestParameter().getParameter().getName());
          accepetedValueDto.setTestParameterId(acceptedValue.getTestParameter().getId());
          if (acceptedValue.getTestParameter().getName() != null) {
            accepetedValueDto.setTestParameterName(acceptedValue.getTestParameter().getName());
          }
          if (acceptedValue.getMaterialSubCategory() != null) {
            accepetedValueDto
                .setMaterialSubCategoryId(acceptedValue.getMaterialSubCategory().getId());
            accepetedValueDto
                .setMaterialSubCategoryName(acceptedValue.getMaterialSubCategory().getName());
          }
          accepetedValueDtolist.add(accepetedValueDto);
        });
    return accepetedValueDtolist;
  }


  @Transactional(readOnly = true)
  public boolean ExistsTestConfigureId(Long testConfigureId) {
    return materialAcceptedValueRepository.existsByTestConfigureId(testConfigureId);
  }

  @Transactional(readOnly = true)
  public List<RawMaterial> findRawMaterialByTestConfigureId(Long testConfigureId) {
    ArrayList<RawMaterial> rawMaterials = new ArrayList<RawMaterial>();
    TestConfigure testConfigure = testConfigureRepository.findById(testConfigureId).get();
    if (testConfigure.getMaterialSubCategory() == null) {
      rawMaterials.addAll(rawMaterialRepository.findByMaterialSubCategoryMaterialCategoryId(
          testConfigure.getMaterialCategory().getId()));
    } else if (testConfigure.getRawMaterial() == null) {
      rawMaterials.addAll(rawMaterialRepository
          .findByMaterialSubCategoryId(testConfigure.getMaterialSubCategory().getId()));
    } else {
      rawMaterials
          .add(rawMaterialRepository.findById(testConfigure.getRawMaterial().getId()).get());
    }
    return rawMaterials;
  }

  @Transactional
  public void upDateTesConfigureType(Long testConfigureId) {
    TestConfigure testConfigure = testConfigureRepository.findById(testConfigureId).get();
    List<MaterialAcceptedValue> materialAcceptedValueList =
        materialAcceptedValueRepository.findByTestConfigureIdAndFinalResultTrue(testConfigureId);
    List<Long> orinalIds = new ArrayList<Long>();
    List<Long> ids = new ArrayList<Long>();
    for (MaterialAcceptedValue materialAcceptedValue : materialAcceptedValueList) {
      ids.add(materialAcceptedValue.getTestParameter().getId());
    }
    orinalIds.addAll(ids.stream().distinct().collect(Collectors.toList()));
    if (orinalIds.isEmpty() || orinalIds.size() == 1) {
      testConfigure.setTestResultType(TestResultType.SINGLE_RESULT);
      testConfigureRepository.save(testConfigure);
    } else {
      testConfigure.setTestResultType(TestResultType.MULTI_RESULT);
      testConfigureRepository.save(testConfigure);
    }
  }

  @Transactional(readOnly = true)
  public List<MaterialSubCategory> getMaterialSubCategoryByTesConfigureId(Long testConfigureId) {
    TestConfigure testConfigure = testConfigureRepository.findById(testConfigureId).get();
    List<MaterialSubCategory> materialSubCategoryList = new ArrayList<>();
    if (testConfigure.getRawMaterial() == null && testConfigure.getMaterialSubCategory() != null) {
      materialSubCategoryList.add(testConfigure.getMaterialSubCategory());
    }
    if (testConfigure.getRawMaterial() == null && testConfigure.getMaterialSubCategory() == null) {
      materialSubCategoryList.addAll(materialSubCategoryRepository
          .findByMaterialCategory(testConfigure.getMaterialCategory()));
    }
    return materialSubCategoryList;
  }

  @Transactional
  public List<RawMaterial> getRawMaterialByTesConfigureId(Long testConfigureId) {
    TestConfigure testConfigure = testConfigureRepository.findById(testConfigureId).get();
    List<RawMaterial> rawMaterialList = new ArrayList<>();
    if (testConfigure.getRawMaterial() != null) {
      rawMaterialList.add(testConfigure.getRawMaterial());
    }
    if (testConfigure.getRawMaterial() == null && testConfigure.getMaterialSubCategory() != null) {
      rawMaterialList.addAll(rawMaterialRepository
          .findByMaterialSubCategoryId(testConfigure.getMaterialSubCategory().getId()));
    }
    if (testConfigure.getRawMaterial() == null && testConfigure.getMaterialSubCategory() == null) {
      rawMaterialList.addAll(rawMaterialRepository.findByMaterialSubCategoryMaterialCategoryId(
          testConfigure.getMaterialCategory().getId()));
    }
    return rawMaterialList;
  }

  public List<RawMaterial> getRawMaterialByTesConfigureIdAndTestParameterId(Long testConfigureId,
      Long testParameterId) {
    List<RawMaterial> rawMaterialList = new ArrayList<>();
    List<RawMaterial> rawMaterialListTotal = getRawMaterialByTesConfigureId(testConfigureId);
    rawMaterialList.addAll(rawMaterialListTotal.stream()
        .filter(rawmaterial -> (materialAcceptedValueRepository
            .findByTestConfigureIdAndTestParameterIdAndRawMaterialId(testConfigureId,
                testParameterId, rawmaterial.getId()) == null))
        .collect(Collectors.toList()));
    return rawMaterialList;
  }

  @Transactional
  public List<MaterialSubCategory> getMaterialSubCategoryByTesConfigureIdAndTestParameterId(
      Long testConfigureId, Long testParameterId) {
    List<MaterialSubCategory> materialSubCategoryList = new ArrayList<>();
    List<MaterialSubCategory> materialSubCategoryListTotal =
        getMaterialSubCategoryByTesConfigureId(testConfigureId);
    materialSubCategoryList.addAll(materialSubCategoryListTotal.stream()
        .filter(materialSubCategory -> (materialAcceptedValueRepository
            .findByTestConfigureIdAndTestParameterIdAndMaterialSubCategoryId(testConfigureId,
                testParameterId, materialSubCategory.getId()) == null))
        .collect(Collectors.toList()));
    return materialSubCategoryList;
  }

  @Transactional(readOnly = true)
  public AcceptedValueMainDto searchAcceptedValue(Long testConfigId,
      CategoryAcceptedType categoryAcceptedType, String testParamName, Condition condition,
      String materialName, BooleanBuilder booleanBuilder, int page, int size, Pageable pageable,
      Pagination pagination) {
    AcceptedValueMainDto acceptedValueMainDto = new AcceptedValueMainDto();
    acceptedValueMainDto.setTestConfigureResDto(
        testConfigureService.getTestConfigureForAcceptedValue(testConfigId));
    if (testParamName != null && !testParamName.isEmpty()) {
      booleanBuilder.and(
          QMaterialAcceptedValue.materialAcceptedValue.testParameter.name.contains(testParamName));
    }
    if (testConfigId != null) {
      booleanBuilder
          .and(QMaterialAcceptedValue.materialAcceptedValue.testConfigure.id.eq(testConfigId));
    }
    if (condition != null) {
      booleanBuilder.and(QMaterialAcceptedValue.materialAcceptedValue.conditionRange.eq(condition));
    }
    if (categoryAcceptedType != null) {
      booleanBuilder.and(QMaterialAcceptedValue.materialAcceptedValue.categoryAcceptedType
          .eq(categoryAcceptedType));
    }
    if (materialName != null && !materialName.isEmpty()) {
      booleanBuilder.and(
          QMaterialAcceptedValue.materialAcceptedValue.rawMaterial.name.contains(materialName));
    }

    pagination.setTotalRecords((long) ((List<MaterialAcceptedValue>) materialAcceptedValueRepository
        .findAll(booleanBuilder)).size());
    acceptedValueMainDto.setAccepetedValueDto(getMaterialAccepetedValueDtoListPage(
        materialAcceptedValueRepository.findAll(booleanBuilder, pageable).toList()));
    return acceptedValueMainDto;
  }

  @Transactional(readOnly = true)
  public boolean isTestConfigureIdAndSubCategoryAndTestParameterId(Long testConfigureId,
      Long materialSubCategoryId, Long testParameterId) {
    return materialAcceptedValueRepository
        .existsByTestConfigureIdAndMaterialSubCategoryIdAndTestParameterId(testConfigureId,
            materialSubCategoryId, testParameterId);
  }



  @Transactional(readOnly = true)
  public Long countMaterialAcceptedValues(Long testConfigureId,
      CategoryAcceptedType categoryAcceptedType) {
    return materialAcceptedValueRepository
        .countByTestConfigureIdAndCategoryAcceptedType(testConfigureId, categoryAcceptedType);
  }

  @Transactional(readOnly = true)
  public AcceptedValueMainDto getAllMaterialAcceptedValuesByPage(Pageable pageable,
      Long testConfigureId, CategoryAcceptedType categoryAcceptedType) {
    AcceptedValueMainDto acceptedValueMainDto = new AcceptedValueMainDto();
    acceptedValueMainDto.setTestConfigureResDto(
        testConfigureService.getTestConfigureForAcceptedValue(testConfigureId));
    acceptedValueMainDto.setAccepetedValueDto(getMaterialAccepetedValueDtoListPage(
        materialAcceptedValueRepository.findByTestConfigureIdAndCategoryAcceptedType(pageable,
            testConfigureId, categoryAcceptedType).toList()));
    return acceptedValueMainDto;
  }

  private List<AccepetedValueDto> getMaterialAccepetedValueDtoListPage(
      List<MaterialAcceptedValue> materialAcc) {
    List<AccepetedValueDto> accepetedValueDtolist = new ArrayList<>();
    materialAcc.stream().forEach(acceptedValue -> {
      AccepetedValueDto accepetedValueDto = new AccepetedValueDto();
      accepetedValueDto.setId(acceptedValue.getId());
      accepetedValueDto.setConditionRange(acceptedValue.getConditionRange().toString());
      accepetedValueDto.setMinValue(acceptedValue.getMinValue());
      accepetedValueDto.setMaxValue(acceptedValue.getMaxValue());
      accepetedValueDto.setFinalResult(acceptedValue.isFinalResult());
      accepetedValueDto.setValue(acceptedValue.getValue());
      accepetedValueDto.setCategoryAcceptedType(acceptedValue.getCategoryAcceptedType());
      if (acceptedValue.getTestEquation() != null) {
        accepetedValueDto.setTestEquationId(acceptedValue.getTestEquation().getId());
        accepetedValueDto
            .setTestEquationFormula(acceptedValue.getTestEquation().getEquation().getFormula());
      }
      if (acceptedValue.getRawMaterial() != null) {
        accepetedValueDto.setMaterialId(acceptedValue.getRawMaterial().getId());
        accepetedValueDto.setMaterialName(acceptedValue.getRawMaterial().getName());
      }
      accepetedValueDto.setParameterName(acceptedValue.getTestParameter().getParameter().getName());
      accepetedValueDto.setTestParameterId(acceptedValue.getTestParameter().getId());
      if (acceptedValue.getTestParameter().getName() != null) {
        accepetedValueDto.setTestParameterName(acceptedValue.getTestParameter().getName());
      }
      if (acceptedValue.getMaterialSubCategory() != null) {
        accepetedValueDto.setMaterialSubCategoryId(acceptedValue.getMaterialSubCategory().getId());
        accepetedValueDto
            .setMaterialSubCategoryName(acceptedValue.getMaterialSubCategory().getName());
      }
      accepetedValueDtolist.add(accepetedValueDto);
    });
    return accepetedValueDtolist;
  }

  @Transactional(readOnly = true)
  public boolean isCheckValidations(MaterialAcceptedValue materialAcceptedValue) {
      if (materialAcceptedValue.getConditionRange().equals(Condition.BETWEEN)) {
        if (materialAcceptedValue.getMaxValue() == null
            || materialAcceptedValue.getMinValue() == null) {
          return true;
        }
      } else if (materialAcceptedValue.getConditionRange().equals(Condition.GREATER_THAN)
          || materialAcceptedValue.getConditionRange().equals(Condition.LESS_THAN)
          || materialAcceptedValue.getConditionRange().equals(Condition.EQUAL)) {
        if (materialAcceptedValue.getValue() == null) {
          return true;
        }
    }
    return false;
  }
}
