package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.MaterialQualityParameterRequestDto;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;

@Service
public class MaterialQualityParameterServiceImpl implements MaterialQualityParameterService {
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;

  @Transactional
  public void saveAllMaterialQualityParameter(
      List<MaterialQualityParameter> materialQualityParameterList) {
    materialQualityParameterRepository.saveAll(materialQualityParameterList);
  }

  @Transactional(readOnly = true)
  public List<MaterialQualityParameter> getAllMaterialQualityParameters() {
    return materialQualityParameterRepository.findAll();
  }

  @Transactional(readOnly = true)
  public void deleteMaterialQualityParameterById(Long id) {
    materialQualityParameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isExistsById(Long id) {
    return materialQualityParameterRepository.existsById(id);
  }

  @Transactional
  public void updateMaterialQualityParameter(MaterialQualityParameter materialQualityParameter) {
    materialQualityParameterRepository.save(materialQualityParameter);
  }

  // check common fields null
  @Transactional(readOnly = true)
  public boolean checkCommonNullFieldValues(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      if ((materialQualityParameterRequestDto.getQualityParameterId() == null)
          || (materialQualityParameterRequestDto.getQualityParamaterType() == null)
          || materialQualityParameterRequestDto.getConditionRange() == null
          || materialQualityParameterRequestDto.getUnitId() == null) {
        return true;
      }
    }
    return false;
  }

  // check null validation for quality parameter type
  @Transactional(readOnly = true)
  public boolean checkValidationByType(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      if ((materialQualityParameterRequestDto.getQualityParamaterType()
          .equals(QualityParamaterType.MATERIAL)
          && materialQualityParameterRequestDto.getRawMaterialId() == null
          || materialQualityParameterRequestDto.getQualityParamaterType()
              .equals(QualityParamaterType.SUB_CATEGORY)
              && materialQualityParameterRequestDto.getMaterialSubCategoryId() == null)) {
        return true;
      }
    }
    return false;
  }

  // check validation for condition range
  @Transactional(readOnly = true)
  public boolean checkValidationForConditionalRange(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      if (materialQualityParameterRequestDto.getConditionRange().equals(Condition.BETWEEN)
          && materialQualityParameterRequestDto.getMaxValue() == null
          && materialQualityParameterRequestDto.getMinValue() == null) {
        return true;
      } else if ((materialQualityParameterRequestDto.getConditionRange()
          .equals(Condition.GREATER_THAN)
          || materialQualityParameterRequestDto.getConditionRange().equals(Condition.LESS_THAN)
          || materialQualityParameterRequestDto.getConditionRange().equals(Condition.EQUAL))
          && materialQualityParameterRequestDto.getValue() == null) {
        return true;
      }
    }
    return false;
  }

  // check duplicate entry
  public boolean checkDuplicateEntry(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      if ((materialQualityParameterRequestDto.getQualityParamaterType()
          .equals(QualityParamaterType.SUB_CATEGORY)
          && materialQualityParameterRepository.existsByQualityParameterIdAndMaterialSubCategoryId(
              materialQualityParameterRequestDto.getQualityParameterId(),
              materialQualityParameterRequestDto.getMaterialSubCategoryId()))
          || (materialQualityParameterRequestDto.getQualityParamaterType()
              .equals(QualityParamaterType.MATERIAL)
              && materialQualityParameterRepository.existsByQualityParameterIdAndRawMaterialId(
                  materialQualityParameterRequestDto.getQualityParameterId(),
                  materialQualityParameterRequestDto.getRawMaterialId()))) {
        return true;
      }
    }
    return false;
  }
}

