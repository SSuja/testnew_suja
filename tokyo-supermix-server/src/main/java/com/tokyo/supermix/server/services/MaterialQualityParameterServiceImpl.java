package com.tokyo.supermix.server.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.tokyo.supermix.data.dto.MaterialQualityParameterRequestDto;
import com.tokyo.supermix.data.entities.MaterialAcceptedValue;
import com.tokyo.supermix.data.entities.MaterialQualityParameter;
import com.tokyo.supermix.data.enums.Condition;
import com.tokyo.supermix.data.enums.QualityParamaterType;
import com.tokyo.supermix.data.repositories.MaterialAcceptedValueRepository;
import com.tokyo.supermix.data.repositories.MaterialQualityParameterRepository;

@Service
public class MaterialQualityParameterServiceImpl implements MaterialQualityParameterService {
  @Autowired
  private MaterialQualityParameterRepository materialQualityParameterRepository;
  @Autowired
  private MaterialAcceptedValueRepository materialAcceptedValueRepository;

  @Transactional
  public void saveAllMaterialQualityParameter(
      List<MaterialQualityParameter> materialQualityParameterList) {
    materialQualityParameterRepository.saveAll(materialQualityParameterList);
  }

  @Transactional(readOnly = true)
  public List<MaterialQualityParameter> getAllMaterialQualityParameters() {
    return materialQualityParameterRepository.findAll();
  }

  @Transactional(propagation = Propagation.NEVER)
  public void deleteMaterialQualityParameterById(Long id) {
    materialQualityParameterRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public boolean isExistsById(Long id) {
    return materialQualityParameterRepository.existsById(id);
  }

  // update materialAcceptedValue while edit materialQualityParameter
  @Transactional
  public void updateMaterialQualityParameter(MaterialQualityParameter materialQualityParameter) {
    if (materialQualityParameter.getQualityParamaterType().equals(QualityParamaterType.MATERIAL)) {
      if (materialAcceptedValueRepository.existsByTestParameterParameterIdAndRawMaterialId(
          materialQualityParameter.getParameter().getId(),
          materialQualityParameter.getRawMaterial().getId())) {
        for (MaterialAcceptedValue materialAcceptedValue : materialAcceptedValueRepository
            .findByTestParameterParameterIdAndRawMaterialId(
                materialQualityParameter.getParameter().getId(),
                materialQualityParameter.getRawMaterial().getId())) {
          setConditionRangeForMaterialAcceptedValue(materialQualityParameter,
              materialAcceptedValue);
        }
      }
    } else {
      if (materialAcceptedValueRepository.existsByTestParameterParameterIdAndMaterialSubCategoryId(
          materialQualityParameter.getParameter().getId(),
          materialQualityParameter.getMaterialSubCategory().getId())) {
        for (MaterialAcceptedValue materialAcceptedValue : materialAcceptedValueRepository
            .findByTestParameterParameterIdAndMaterialSubCategoryId(
                materialQualityParameter.getParameter().getId(),
                materialQualityParameter.getMaterialSubCategory().getId())) {
          setConditionRangeForMaterialAcceptedValue(materialQualityParameter,
              materialAcceptedValue);
        }
      }
    }
    materialQualityParameterRepository.save(materialQualityParameter);
  }

  // set condition range for materialAcceptedValue while edit materialQualityParameter
  public void setConditionRangeForMaterialAcceptedValue(
      MaterialQualityParameter materialQualityParameter,
      MaterialAcceptedValue materialAcceptedValue) {
    if (materialQualityParameter.getConditionRange() != null) {
      if (materialQualityParameter.getConditionRange().equals(Condition.BETWEEN)) {
        materialAcceptedValue.setConditionRange(materialQualityParameter.getConditionRange());
        materialAcceptedValue.setMaxValue(materialQualityParameter.getMaxValue());
        materialAcceptedValue.setMinValue(materialQualityParameter.getMinValue());
      } else if (materialQualityParameter.getConditionRange().equals(Condition.EQUAL)
          || materialQualityParameter.getConditionRange().equals(Condition.GREATER_THAN)
          || materialQualityParameter.getConditionRange().equals(Condition.LESS_THAN)) {
        materialAcceptedValue.setConditionRange(materialQualityParameter.getConditionRange());
        materialAcceptedValue.setValue(materialQualityParameter.getValue());
      }
    }
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
