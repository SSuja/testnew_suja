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

  @Transactional(readOnly = true)
  public List<MaterialQualityParameter> getAllMaterialParametersByRawMaterial(Long rawMaterialId) {
    return materialQualityParameterRepository.findByRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public List<MaterialQualityParameter> getAllMaterialParametersBySubCategory(Long subCategoryId) {
    return materialQualityParameterRepository.findByMaterialSubCategoryId(subCategoryId);
  }

  @Transactional(readOnly = true)
  public boolean existsMaterialParametersByRawMaterial(Long rawMaterialId) {
    return materialQualityParameterRepository.existsByRawMaterialId(rawMaterialId);
  }

  @Transactional(readOnly = true)
  public boolean existsMaterialParametersBySubCategory(Long subCategoryId) {
    return materialQualityParameterRepository.existsByMaterialSubCategoryId(subCategoryId);
  }

  // check common fields null
  @Transactional(readOnly = true)
  public boolean checkCommonNullFieldValues(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      if ((materialQualityParameterRequestDto.getParameterId() == null)
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
  @Transactional(readOnly = true)
  public boolean checkDuplicateEntry(
      List<MaterialQualityParameterRequestDto> materialQualityParameterRequestDtoList) {
    for (MaterialQualityParameterRequestDto materialQualityParameterRequestDto : materialQualityParameterRequestDtoList) {
      if ((materialQualityParameterRequestDto.getQualityParamaterType()
          .equals(QualityParamaterType.SUB_CATEGORY)
          && materialQualityParameterRepository.existsByParameterIdAndMaterialSubCategoryId(
              materialQualityParameterRequestDto.getParameterId(),
              materialQualityParameterRequestDto.getMaterialSubCategoryId()))
          || (materialQualityParameterRequestDto.getQualityParamaterType()
              .equals(QualityParamaterType.MATERIAL)
              && materialQualityParameterRepository.existsByParameterIdAndRawMaterialId(
                  materialQualityParameterRequestDto.getParameterId(),
                  materialQualityParameterRequestDto.getRawMaterialId()))) {
        return true;
      }
    }
    return false;
  }

  // check duplicate entry for update
  @Transactional(readOnly = true)
  public boolean checkAlreadyExistsForUpdate(
      MaterialQualityParameterRequestDto materialQualityParameterRequestDto) {
    MaterialQualityParameter materialQualityParameter = materialQualityParameterRepository
        .findById(materialQualityParameterRequestDto.getId()).get();
    if ((materialQualityParameterRequestDto.getQualityParamaterType()
        .equals(QualityParamaterType.MATERIAL)
        && !(materialQualityParameter.getParameter().getId()
            .equals(materialQualityParameterRequestDto.getParameterId())
            && materialQualityParameter.getRawMaterial().getId()
                .equals(materialQualityParameterRequestDto.getRawMaterialId()))
        && materialQualityParameterRepository.existsByParameterIdAndRawMaterialId(
            materialQualityParameterRequestDto.getParameterId(),
            materialQualityParameterRequestDto.getRawMaterialId()))
        || (materialQualityParameterRequestDto.getQualityParamaterType()
            .equals(QualityParamaterType.SUB_CATEGORY)
            && !(materialQualityParameter.getParameter().getId()
                .equals(materialQualityParameterRequestDto.getParameterId())
                && materialQualityParameter.getMaterialSubCategory().getId()
                    .equals(materialQualityParameterRequestDto.getMaterialSubCategoryId()))
            && materialQualityParameterRepository.existsByParameterIdAndMaterialSubCategoryId(
                materialQualityParameterRequestDto.getParameterId(),
                materialQualityParameterRequestDto.getMaterialSubCategoryId()))) {
      return true;
    }
    return false;
  }
}
